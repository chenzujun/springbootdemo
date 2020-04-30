package com.raine.springboot.demo.common.shiro;

import com.raine.springboot.demo.dao.IUserMapper;
import com.raine.springboot.demo.domain.Permission;
import com.raine.springboot.demo.domain.Role;
import com.raine.springboot.demo.domain.User;
import com.raine.springboot.demo.service.IAuthService;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private IUserMapper userMapper;
    @Autowired
    private IAuthService authService;

    /**
     * 授予角色和权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        List<Role> roles = this.authService.getRoleByUser(user.getId());
        if (null != roles && roles.size() > 0) {
            for (Role role : roles) {
                authorizationInfo.addRole(role.getCode());
                List<Permission> perms = this.authService.findPermsByRoleId(role.getId());
                if (null != perms && perms.size() > 0) {
                    for (Permission perm : perms) {
                        authorizationInfo.addStringPermission(perm.getCode());
                    }
                }
            }
        }
        return authorizationInfo;
    }

    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        logger.info("用户登录认证：验证当前Subject时获取到token为：{}", ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        User user = userMapper.findUserByMobile(token.getUsername());
        if (user == null) {
            return null;
        }
        logger.debug("用户登录认证！用户信息user：{}", user.toString());
        // 密码存在
        // 第一个参数 ，登陆后，需要在session保存数据
        // 第二个参数，查询到密码(加密规则要和自定义的HashedCredentialsMatcher中的HashAlgorithmName散列算法一致)
        // 第三个参数 ，realm名字
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                DigestUtils.md5Hex(user.getPassword()),
                getName()
        );
        return authenticationInfo;
    }

}
