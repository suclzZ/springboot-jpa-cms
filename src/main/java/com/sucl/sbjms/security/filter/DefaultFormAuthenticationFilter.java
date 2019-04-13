package com.sucl.sbjms.security.filter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 登录拦截器
 * 如果交给spring管理则会出现问题
 *
 * @author sucl
 * @date 2019/4/12
 */
//@Component("formAuthc")
public class DefaultFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return super.onAccessDenied(request, response);
    }

    @Override
    protected boolean isLoginSubmission(ServletRequest request, ServletResponse response) {
        return super.isLoginSubmission(request, response);
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        return super.createToken(request, response);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        return super.onLoginSuccess(token, subject, request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        return super.onLoginFailure(token, e, request, response);
    }

    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        super.setFailureAttribute(request, ae);
    }
}
