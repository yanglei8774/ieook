package cn.aouo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hanbo on 2016/8/9.
 * hanbo
 */
@SpringBootApplication(scanBasePackages = "cn.aouo")
@EnableScheduling
public class ApplicationInitializer extends WebMvcConfigurerAdapter implements ApplicationListener<ContextRefreshedEvent> {

    @Resource(name = "SYS_SEC_AuthenticationProvider")
    private AuthenticationProvider custmAuthenticationProvider;

    @Resource(name = "SYS_SEC_AuthenticationFailureHandler")
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 配置拦截器
     *
     * @param registry
     * @author lance
     */
    public void addInterceptors(InterceptorRegistry registry) {
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationInitializer.class, args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    }


    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        public ApplicationSecurity(){
            super(true);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            {
                http
                        .csrf().and()
//                        .addFilter(new WebAsyncManagerIntegrationFilter())
                        .exceptionHandling().and()
                        .headers().and()
                        .sessionManagement().and()
                        .securityContext().and()
                        .requestCache().and()
                        .anonymous().and()
                        .servletApi().and()
                        .apply(new DefaultLoginPageConfigurer<HttpSecurity>()).and()
                        .logout();
                // @formatter:on
                ClassLoader classLoader = this.getApplicationContext().getClassLoader();
                List<AbstractHttpConfigurer> defaultHttpConfigurers =
                        SpringFactoriesLoader.loadFactories(AbstractHttpConfigurer.class, classLoader);

                for(AbstractHttpConfigurer configurer : defaultHttpConfigurers) {
                    http.apply(configurer);
                }
            }
            http
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
//                    .loginProcessingUrl("/login")
                    //.failureHandler(authenticationFailureHandler)
                    .defaultSuccessUrl("/")
                    .permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/index")
                    .permitAll()
                    .invalidateHttpSession(true)
                    .deleteCookies("_u")
                    .clearAuthentication(true)
                    .and()

                    .csrf()
                    .disable();
        }

        @Override
        public void configure(WebSecurity webSecurity) throws Exception {
            webSecurity.ignoring().antMatchers(
                    "/",                     //根目录
                    "/media/**",
                    "/loginPage/**",
                    "/user/register/**",
                    "/**/register/**",
                    "/redirect/**",
                    "/api/**",
                    "/index/**",
                    "/download/**",
                    "/login_error",                  //登录错误页
                    "/wechat/**"                  //
                    );

        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(custmAuthenticationProvider);
        }

    }
}
