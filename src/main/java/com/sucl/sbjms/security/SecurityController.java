package com.sucl.sbjms.security;

import com.sucl.sbjms.core.rem.Message;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sucl
 * @date 2019/4/13
 */
@RestController
public class SecurityController {

    @PostMapping("/dologin")
    public Message login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken toker = new UsernamePasswordToken(username,password,rememberMe);
        Message message = new Message(Message.SUCCESS_CODE,username);
        try {
            boolean reLogin = true;//重新登录
            if(subject.isAuthenticated()){//已经登录如果再次登录
                if(reLogin){
                    subject.logout();
                }else{
                    return message;
                }
            }
            subject.login(toker);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            message.setCode(Message.FAILURE_CODE);
            message.setInfo(loginFailure(e));
        }
        return message;
    }

    private String loginFailure(AuthenticationException e) {
        String msg = "登录失败！";
        if(e instanceof AccountException){
            msg = "账号异常！";
            if(e instanceof DisabledAccountException){
                msg = "账号不可用！";
                if(e instanceof  LockedAccountException){
                    msg = "账号已锁定！";
                }
            }else if(e instanceof ConcurrentAccessException){
                msg = "多用户登录异常！";
            }else if(e instanceof  ExcessiveAttemptsException){
                msg = "账号认证失败次数过多！";
            }else if(e instanceof UnsupportedTokenException){
                msg = "未知的账号！";
            }
        }else if(e instanceof CredentialsException){
            msg = "密码异常！";
            if(e instanceof IncorrectCredentialsException){
                msg = "密码不正确！";
            }else if(e instanceof ExpiredCredentialsException){
                msg = "密码过期！";
            }
        }else if(e instanceof UnsupportedTokenException){
            msg = "未知的令牌！";
        }
        return msg;
    }
}
