package cn.aouo.common.util;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liu.y on 2017/8/18.
 */
@Controller
public class JumpUtilController {


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        return "index";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index2(HttpServletRequest request) {
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(HttpServletRequest request) {
        return "register";
    }

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String main(HttpServletRequest request) {
        return "ow/html/main";
    }







}
