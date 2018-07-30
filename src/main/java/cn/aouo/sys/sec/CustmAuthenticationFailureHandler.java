package cn.aouo.sys.sec;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ForwardAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("SYS_SEC_AuthenticationFailureHandler")
public class CustmAuthenticationFailureHandler extends ForwardAuthenticationFailureHandler {

//    @Resource
//    private RedisConnectionFactory redisConnectionFactory;

    public CustmAuthenticationFailureHandler() {
        super("/login_error");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        if(!"PHONENUM_PASSWORD".equals(request.getAttribute("SPRING_SECURITY_LOGIN_MODE"))){
           // long counter = this.incrementCounter(username);
            request.setAttribute("SPRING_SECURITY_LOGIN_ERROR_TIMES", 1);
        }
        super.onAuthenticationFailure(request, response, exception);
    }

    /*private long incrementCounter(String username){
        username = "neusoft:login:errorCounter:"+username;
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            long counter = connection.incr(username.getBytes("utf-8"));
            connection.expire(username.getBytes("utf-8"), 300);//5分钟
            return counter;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("不支持UTF-8字符集编码", e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }*/
}
