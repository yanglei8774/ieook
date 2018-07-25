package cn.aouo.sys.controller;

import cn.aouo.common.util.RequestResult;
import cn.aouo.sys.entity.User;
import cn.aouo.sys.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2018/7/25.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/list")
    @ResponseBody
    public RequestResult getUserList() {
        RequestResult result = new RequestResult();
        List<User> userList = new ArrayList<>();
        try {
            userList = userService.getUserList();
            result.setResult(true, "查询用户成功", userList);
        } catch (Exception e) {
            logger.error("查询异常",e);
            result.setResult(false, "查询用户异常");
        }
        return result;
    }


}