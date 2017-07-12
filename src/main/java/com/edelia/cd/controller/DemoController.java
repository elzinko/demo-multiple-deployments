package com.edelia.cd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edelia.cd.service.DemoService;

@RestController
public class DemoController {
	
    @Autowired
    private DemoService greetingService;

    @RequestMapping("/")
    public String getIP() {
        return greetingService.getIP();
    }

}