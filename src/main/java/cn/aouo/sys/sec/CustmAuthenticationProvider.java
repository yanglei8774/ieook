package cn.aouo.sys.sec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.regex.Pattern;


@Component("SYS_SEC_AuthenticationProvider")
public class CustmAuthenticationProvider implements AuthenticationProvider {

    private Logger logger = LoggerFactory.getLogger(CustmAuthenticationProvider.class);

    /**
     * 手机号格式，以1开头，共11位
     */
    private Pattern phoneNumPattern = Pattern.compile("^1\\d{10}$");

    private int SMS_CODE_EXPIRE_MINITUE = 5;

    private int captchaLength = 4;

    private long SMS_CODE_EXPIRE_Millis = SMS_CODE_EXPIRE_MINITUE * 60 * 1000;

    private PasswordEncoder passwordEncoder = new Md5PasswordEncoder();


    @Resource(name = "SYS_SEC_UserDetailService")
    private UserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
       // long errorTimes = this.getLoginErrorCounter(username);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //String  passwordtype=request.getParameter("passwordtype").toString();
        String  passwordtype="1";
        if("1".equals(passwordtype)){
            return this.doAuthenticate(authentication);
        } else {
            throw new BadCredentialsException("请输入正确的验证码");

        }
    }

    private Authentication doAuthenticate(Authentication authentication){
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        AppUser userDetails;

        //检查用户名有效性
        userDetails = (AppUser) userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("用户名错误");
        }

        if(!userDetails.isAccountNonLocked()){
            throw new LockedException("账户已锁定");
        }

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //String  passwordtype=request.getParameter("passwordtype").toString();
        //if("0".equals(passwordtype)){//密码
            request.setAttribute("SPRING_SECURITY_LOGIN_MODE","USERNAME_PASSWORD");
            if (passwordEncoder.isPasswordValid(userDetails.getPassword(), password, null)) {
//            if (userDetails.getPassword().equals(password)) {
                Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
                try {
                    response.addCookie(new Cookie("_u", Base64.encodeBase64String((userDetails.getUserId()+"  "+userDetails.getUsername()).getBytes("utf-8"))));
                } catch (UnsupportedEncodingException e) {
                    logger.error("不支持UTF-8字符集",e);
                }
                return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
            }else{
                throw new CredentialsExpiredException("用户名或密码错误！");
            }
        //}
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
