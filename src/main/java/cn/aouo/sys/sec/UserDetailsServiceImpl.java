package cn.aouo.sys.sec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by liu.w on 2017/8/31.
 */
@Component("SYS_SEC_UserDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {


    private static final Integer LOCKED = 1;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String account = username;
        String password = username;
        if(password==null){
            password="";
        }
        boolean locked = LOCKED.equals("1");
        AppUser u = new AppUser(account,password,new ArrayList(0), locked);
        u.setUserId("1");
        return u;
    }
}
