package com.edelia.cd.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetinControllerITest {

    private TestRestTemplate rest = new TestRestTemplate();

    @Autowired
    EmbeddedWebApplicationContext tomcat;

    int port;
    String baseUri;

    @Before
    public void before() {
        port = tomcat.getEmbeddedServletContainer().getPort();
        baseUri = "http://localhost:" + port;
    }

    @Test
    public void shouldExposeApi() throws InterruptedException {
        String ip = rest.getForObject(baseUri + "/", String.class);
        Assert.notNull(ip, "result should not ne null");
    }


}