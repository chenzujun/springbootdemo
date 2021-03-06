package com.raine.springboot.demo.controller;

import com.raine.springboot.demo.common.enums.EnumSystemStatus;
import com.raine.springboot.demo.domain.User;
import com.raine.springboot.demo.domain.base.ResponseResult;
import com.raine.springboot.demo.domain.vo.UserVo;
import com.raine.springboot.demo.service.IUserService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenjun
 * @date 2020/4/30
 * @since V1.0.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private EhCacheManager ecm;

    /**
     * 登录【使用shiro中自带的HashedCredentialsMatcher结合ehcache（记录输错次数）配置进行密码输错次数限制】
     * </br>缺陷是，无法友好的在后台提供解锁用户的功能，当然，可以直接提供一种解锁操作，清除ehcache缓存即可，不记录在用户表中；
     * </br>
     *
     * @param user
     * @param rememberMe
     * @return
     */
    @RequestMapping(value = "in", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(UserVo user,
                                @RequestParam(value = "rememberMe", required = false) boolean rememberMe) {
        logger.debug("用户登录，请求参数=user:" + user + "，是否记住我：" + rememberMe);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(EnumSystemStatus.ERROR.getCode());
        if (null == user) {
            responseResult.setCode(EnumSystemStatus.PARAM_ERROR.getCode());
            responseResult.setMessage("请求参数有误，请您稍后再试");
            logger.debug("用户登录，结果=responseResult:" + responseResult);
            return responseResult;
        }
        if (!validatorRequestParam(user, responseResult)) {
            logger.debug("用户登录，结果=responseResult:" + responseResult);
            return responseResult;
        }
        // 用户是否存在
        User existUser = this.userService.findUserByMobile(user.getMobile());
        if (existUser == null) {
            responseResult.setMessage("该用户不存在，请您联系管理员");
            logger.debug("用户登录，结果=responseResult:" + responseResult);
            return responseResult;
        } else {
            // 是否离职
            if (existUser.getIsJob()) {
                responseResult.setMessage("登录用户已离职，请您联系管理员");
                logger.debug("用户登录，结果=responseResult:" + responseResult);
                return responseResult;
            }
//            // 校验验证码
//            if (!existUser.getMcode().equals(user.getSmsCode())) {
//                responseResult.setCode(EnumSystemStatus.PARAM_ERROR.getCode());
//                responseResult.setMessage("短信验证码输入有误");
//                logger.debug("用户登录，结果=responseResult:" + responseResult);
//                return responseResult;
//            } //1分钟
//            long beginTime = existUser.getSendTime().getTime();
//            long endTime = System.currentTimeMillis();
//            if (((endTime - beginTime) - 60000 > 0)) {
//                responseResult.setCode(EnumSystemStatus.PARAM_ERROR.getCode());
//                responseResult.setMessage("短信验证码超时");
//                logger.debug("用户登录，结果=responseResult:" + responseResult);
//                return responseResult;
//            }
        }
        // 用户登录
        try {
            // 1、 封装用户名、密码、是否记住我到token令牌对象 [支持记住我]
            AuthenticationToken token = new UsernamePasswordToken(
                    user.getMobile(),
                    DigestUtils.md5Hex(user.getPassword()),
                    rememberMe);
            // 2、 Subject调用login
            Subject subject = SecurityUtils.getSubject();
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.debug("用户登录，用户验证开始！user=" + user.getMobile());
            subject.login(token);
            responseResult.setCode(EnumSystemStatus.SUCCESS.getCode());
            logger.info("用户登录，用户验证通过！user=" + user.getMobile());
        } catch (UnknownAccountException uae) {
            logger.error("用户登录，用户验证未通过：未知用户！user=" + user.getMobile(), uae);
            responseResult.setMessage("该用户不存在，请您联系管理员");
        } catch (IncorrectCredentialsException ice) {
            // 获取输错次数
            logger.error("用户登录，用户验证未通过：错误的凭证，密码输入错误！user=" + user.getMobile(), ice);
            responseResult.setMessage("用户名或密码不正确");
        } catch (LockedAccountException lae) {
            logger.error("用户登录，用户验证未通过：账户已锁定！user=" + user.getMobile(), lae);
            responseResult.setMessage("账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            logger.error("用户登录，用户验证未通过：错误次数大于5次,账户已锁定！user=.getMobile()" + user, eae);
            responseResult.setMessage("用户名或密码错误次数大于5次,账户已锁定!</br><span style='color:red;font-weight:bold; '>2分钟后可再次登录，或联系管理员解锁</span>");
        } catch (AuthenticationException ae) {
            // 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.error("用户登录，用户验证未通过：认证异常，异常信息如下！user=" + user.getMobile(), ae);
            responseResult.setMessage("用户名或密码不正确");
        } catch (Exception e) {
            logger.error("用户登录，用户验证未通过：操作异常，异常信息如下！user=" + user.getMobile(), e);
            responseResult.setMessage("用户登录失败，请您稍后再试");
        }
        Cache<String, AtomicInteger> passwordRetryCache = ecm.getCache("passwordRetryCache");
        if (null != passwordRetryCache) {
            int retryNum = (passwordRetryCache.get(existUser.getMobile()) == null ? 0
                    : passwordRetryCache.get(existUser.getMobile())).intValue();
            logger.debug("输错次数：" + retryNum);
            if (retryNum > 0 && retryNum < 6) {
                responseResult.setMessage("用户名或密码错误" + retryNum + "次,再输错"
                        + (6 - retryNum) + "次账号将锁定");
            }
        }
        logger.debug("用户登录，user=" + user.getMobile() + ",登录结果=responseResult:" + responseResult);
        return responseResult;
    }

    /**
     * 登录【使用redis和mysql实现，用户密码输错次数限制，和锁定解锁用户的功能
     * 该实现后续会提供！TODO
     *
     * @param user
     * @param rememberMe
     * @return
     */
    @RequestMapping(value = "logina", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult logina(UserVo user,
                                 @RequestParam(value = "rememberMe", required = false) boolean rememberMe) {
        logger.debug("用户登录，请求参数=user:" + user + "，是否记住我：" + rememberMe);
        ResponseResult responseResult = new ResponseResult();
        return responseResult;
    }

    @RequestMapping(value = "out", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult logout() {
        ResponseResult responseResult = new ResponseResult();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        responseResult.setCode(EnumSystemStatus.SUCCESS.getCode());
        return responseResult;
    }

    protected boolean validatorRequestParam(Object obj, ResponseResult response) {
        boolean flag = false;
        Validator validator = new Validator();
        List<ConstraintViolation> ret = validator.validate(obj);
        if (ret.size() > 0) {
            // 校验参数有误
            response.setCode(EnumSystemStatus.PARAM_ERROR.getCode());
            response.setMessage(ret.get(0).getMessageTemplate());
        } else {
            flag = true;
        }
        return flag;
    }
}
