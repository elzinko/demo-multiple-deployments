package com.edelia.cd.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class DemoService {

    @Value("${message}")
    private String message;

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

}