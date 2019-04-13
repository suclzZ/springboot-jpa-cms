package com.sucl.sbjms.system.service.impl;

import com.sucl.sbjms.core.service.impl.BaseServiceImpl;
import com.sucl.sbjms.security.realm.IUser;
import com.sucl.sbjms.security.realm.PrincipalService;
import com.sucl.sbjms.system.dao.UserDao;
import com.sucl.sbjms.system.entity.User;
import com.sucl.sbjms.system.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sucl
 * @date 2019/4/2
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserDao,User> implements UserService ,PrincipalService {
    @Override
    protected Class<User> getDomainClazz() {
        return User.class;
    }

    @Override
    public IUser getUser(String username) {
        User user = dao.findByUsername(username);
        if(user==null){
            return null;
        }
        return new IUser() {
            @Override
            public String getUsername() {
                return user.getUsername();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }
        };
    }
}
