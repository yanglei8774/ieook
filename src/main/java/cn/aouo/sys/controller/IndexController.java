package cn.aouo.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by hanbo on 2016/8/10.
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @PostMapping("/aaaa")
    @ResponseBody
    public String forwardList() {
        logger.info("我来了！@");
        return "indexsssss";
    }



}
