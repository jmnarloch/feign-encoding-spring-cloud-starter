package com.github.jmnarloch.spring.cloud.feign.app.configuration;

import com.netflix.loadbalancer.ILoadBalancer;
import feign.Client;
import feign.Feign;
import feign.httpclient.ApacheHttpClient;
import feign.ribbon.LBClientFactory;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 *
 */
@Configuration
@ConditionalOnClass({ ILoadBalancer.class, Feign.class })
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class FeignRibbonClientAutoConfiguration {

    @Configuration
    @ConditionalOnClass(ApacheHttpClient.class)
    public static class HttpClientConfiguration {

        @Autowired(required = false)
        private HttpClient httpClient;

        @Resource(name = "cachingLBClientFactory")
        private LBClientFactory lbClientFactory;

        @Bean
        public Client feignClient() {
            feign.ribbon.RibbonClient.Builder builder = feign.ribbon.RibbonClient.builder();

            if (httpClient != null) {
                builder.delegate(new ApacheHttpClient(httpClient));
            } else {
                builder.delegate(new ApacheHttpClient());
            }

            if (lbClientFactory != null) {
                builder.lbClientFactory(lbClientFactory);
            }

            return builder.build();
        }
    }
}
