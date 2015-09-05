package com.github.jmnarloch.spring.cloud.feign;

import com.github.jmnarloch.spring.cloud.feign.app.client.InvoiceClient;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collections;

/**
 * Demonstrates usage of this component.
 *
 * @author Jakub Narloch
 */
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
@SpringApplicationConfiguration(classes = {Demo.Application.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class Demo {

    @Autowired
    private InvoiceClient invoiceClient;

    @Test
    public void compressedResponse() {

        invoiceClient.getInvoices();
    }

    @EnableFeignAcceptGzipEncoding
    @EnableFeignClients
    @RibbonClient(name = "local", configuration = LocalRibbonClientConfiguration.class)
    @ComponentScan("com.github.jmnarloch.spring.cloud.feign.app")
    @EnableAutoConfiguration
    @Configuration
    public static class Application {
    }

    @Configuration
    static class LocalRibbonClientConfiguration {

        @Value("${local.server.port}")
        private int port = 0;

        @Bean
        public ILoadBalancer ribbonLoadBalancer() {
            BaseLoadBalancer balancer = new BaseLoadBalancer();
            balancer.setServersList(Collections.singletonList(new Server("localhost", this.port)));
            return balancer;
        }
    }
}