package cn.aouo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Tomcat容器方式启动spring boot应用
 * Created by wany on 2016/6/5.
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的IssueApplication启动类
        return builder.sources(ApplicationInitializer.class);
    }
}
