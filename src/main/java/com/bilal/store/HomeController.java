package com.bilal.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Value("${spring.application.name}")
    private String appName;
    @Value("${app.page-size}")
    private String pageSize;

    @RequestMapping("/")
    public String index() {
        System.out.println(appName);
        System.out.println(pageSize);
        return "index.html";
    }
}
