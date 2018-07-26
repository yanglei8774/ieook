package cn.aouo.sys.sec;

import cn.aouo.sys.entity.User;
import cn.aouo.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu.w on 2017/8/31.
 */
@Component("SYS_SEC_UserDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    private static final Integer LOCKED = 1;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String account = username;
        List<User> users = userService.getUserByAccount(account);
        int usersNum = users.size();
        if(usersNum == 0){
            throw new UsernameNotFoundException("没有找到"+username+"对应的用户信息");
        } else if(usersNum > 1){
            throw new UsernameNotFoundException("账户歧义，请通过短信方式登录！");
        }
        User user = users.get(0);
        String password = user.getPassword();
        if(password==null){
            password="";
        }
        boolean locked = LOCKED.equals("1");
        AppUser u = new AppUser(account,password,new ArrayList(0), locked);
        u.setUserId(user.getId());
        return u;
    }
}
