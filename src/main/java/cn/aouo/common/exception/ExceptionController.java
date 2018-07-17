package cn.aouo.common.exception;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 重写BasicErrorController,主要负责系统的异常页面的处理以及错误信息的显示
 * import javax.servlet.http.HttpServletResponse;
 */
@Controller
@RequestMapping(value = "/error")
@EnableConfigurationProperties({ServerProperties.class})
public class ExceptionController implements ErrorController, InitializingBean {

    private ErrorAttributes errorAttributes;


    /**
     * 初始化ExceptionController
     *
     * @param errorAttributes
     */
    @Autowired
    public ExceptionController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("12")
    public ModelAndView errorHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int statusCode = getStatus(request).value();
        response.setStatus(statusCode);
        if(statusCode == 404 || statusCode == 501) {
            response.setContentType(MediaType.TEXT_HTML_VALUE);
            return new ModelAndView("error/"+statusCode);
        }
        response.sendError(statusCode);
        return null;
    }



    @Override
    public void afterPropertiesSet() throws Exception {
    }

    /**
     * 获取错误编码
     *
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    /**
     * 实现错误路径,暂时无用
     *
     * @return
     */
    @Override
    public String getErrorPath() {
        return "";
    }

}
