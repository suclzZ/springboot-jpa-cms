package com.sucl.sbjms.security.realm;

/**
 * @author sucl
 * @date 2019/4/13
 */
public interface PrincipalService {

    public IUser getUser(String username);
}
