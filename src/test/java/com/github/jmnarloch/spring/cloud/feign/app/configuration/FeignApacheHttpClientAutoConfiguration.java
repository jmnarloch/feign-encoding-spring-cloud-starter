package com.github.jmnarloch.spring.cloud.feign.app.configuration;

import feign.Client;
import feign.Feign;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 */
@Configuration
@ConditionalOnClass({Feign.class})
public class FeignApacheHttpClientAutoConfiguration {

    @Configuration
    @ConditionalOnClass(ApacheHttpClient.class)
    public static class HttpClientConfiguration {

        @Autowired(required = false)
        private HttpClient httpClient;

        @Bean
        public Client feignClient() {
            if (httpClient != null) {
                return new ApacheHttpClient(httpClient);
            }
            return new ApacheHttpClient();
        }
    }
}
