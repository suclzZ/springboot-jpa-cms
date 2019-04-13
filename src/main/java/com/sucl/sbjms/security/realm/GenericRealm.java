package com.sucl.sbjms.security.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sucl
 * @date 2019/4/12
 */
public class GenericRealm extends AuthorizingRealm {
    @Autowired(required = false)
    private PrincipalService principalService;

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Object principal = token.getPrincipal();
        if(principalService!=null){
            IUser user = principalService.getUser(principal.toString());
            if(user!=null){
                AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),"");
                return authenticationInfo;
            }
        }
        return null;
    }

    /**
     * 鉴权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
