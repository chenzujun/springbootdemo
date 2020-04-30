package com.raine.springboot.demo.common.config;

import com.raine.springboot.demo.common.shiro.MyShiroRealm;
import com.raine.springboot.demo.common.shiro.RetryLimitHashedCredentialsMatcher;
import com.raine.springboot.demo.common.shiro.ShiroFilterMapFactory;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;


import java.io.IOException;


@Configuration
public class ShiroConfig {
	private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

	/**
	 * 处理拦截资源文件过滤器
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager);

		bean.setLoginUrl("/login");
		bean.setSuccessUrl("/");
		bean.setUnauthorizedUrl("/error/403");

		bean.setFilterChainDefinitionMap(ShiroFilterMapFactory.shiroFilterMap());
		return bean;
	}

	@Bean
	public AuthorizingRealm shiroRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
		MyShiroRealm shiroRealm = new MyShiroRealm();
		shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
		return shiroRealm;
	}

	/**
	 * 权限管理，配置主要是Realm的管理认证
	 *
	 * @return
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(Realm shiroRealm, org.apache.shiro.cache.CacheManager cacheManager, RememberMeManager manager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm);
		// 注入ehcache缓存管理器;
		securityManager.setCacheManager(cacheManager);
		// 注入Cookie记住我管理器
		securityManager.setRememberMeManager(manager);
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	/**
	 * session过期控制
	 *
	 * @return
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager defaultWebSessionManager=new DefaultWebSessionManager();
		// 设置session过期时间3600s
		Long timeout=60L*1000*60;
		defaultWebSessionManager.setGlobalSessionTimeout(timeout);
		return defaultWebSessionManager;
	}

	/**
	 * 凭证匹配器
	 *
	 * @return hashedCredentialsMatcher
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher(EhCacheManager ehCacheManager){
		HashedCredentialsMatcher hashedCredentialsMatcher =
				new RetryLimitHashedCredentialsMatcher(ehCacheManager);
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		hashedCredentialsMatcher.setHashIterations(1);
		return hashedCredentialsMatcher;
	}

	/**
	 * 记住我的配置
	 * @return
	 */
	@Bean
	public RememberMeManager rememberMeManager() {
		Cookie cookie = new SimpleCookie("rememberMe");
		// 通过js脚本将无法读取到cookie信息
		cookie.setHttpOnly(true);
		// cookie保存一天
		cookie.setMaxAge(60 * 60 * 24);
		CookieRememberMeManager manager=new CookieRememberMeManager();
		manager.setCookie(cookie);
		return manager;
	}

	/**
	 * ehcache缓存管理器；shiro整合ehcache：
	 * 通过安全管理器：securityManager
	 * 单例的cache防止热部署重启失败
	 * @return EhCacheManager
	 */
	@Bean
	public EhCacheManager ehCacheManager() {
		EhCacheManager ehcache = new EhCacheManager();
		CacheManager cacheManager = CacheManager.getCacheManager("shiro");
		if(cacheManager == null){
			try {
				cacheManager = CacheManager.create(ResourceUtils.getInputStreamForPath("classpath:config/ehcache.xml"));
			} catch (CacheException | IOException e) {
				logger.error("cacheManager 初始化失败", e);
			}
		}
		ehcache.setCacheManager(cacheManager);
		return ehcache;
	}

	/**
	 * 启用shiro方言，这样能在页面上使用shiro标签
	 * @return
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

	/**
	 * 启用shiro注解
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			org.apache.shiro.mgt.SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

}
