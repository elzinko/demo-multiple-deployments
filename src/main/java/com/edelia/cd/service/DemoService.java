package com.edelia.cd.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class DemoService {

	@Autowired
    private RestTemplate restTemplate;

    @Value("${message}")
    private String message;

    @Value("${property}")
    private String property;

    @Value("${username}")
    private String username;

    @Value("${secret}")
    private String secret;

    @HystrixCommand(fallbackMethod = "IPFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String getIP() {
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Error when trying to get local IP address", e);
        }
        return message.concat(" : ").concat(ip.getHostAddress());
    }

    public String IPFallback() {
    	return "IP Fallback";
    }

    @HystrixCommand(fallbackMethod = "externalServiceFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String getExternalService() {
        return restTemplate.getForObject("http://spring-boot-webmvc", String.class);
    }

    public String externalServiceFallback() {
        return "externalService Fallback";
    }
    
    public String getProperties() {
        return "<h1>Properties</h1><br>"
                .concat("message : ").concat(message).concat("<br>")
                .concat("property : ").concat(property).concat("<br>")
                .concat("username : ").concat(username).concat("<br>")
                .concat("secret : ").concat(secret);

    }
}