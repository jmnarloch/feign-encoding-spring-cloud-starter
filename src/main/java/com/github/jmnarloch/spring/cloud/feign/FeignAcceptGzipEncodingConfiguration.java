package com.github.jmnarloch.spring.cloud.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures the Feign response compression.
 *
 * @author Jakub Narloch
 * @see FeignAcceptGzipEncodingInterceptor
 */
@Configuration
public class FeignAcceptGzipEncodingConfiguration {

    @Bean
    public FeignAcceptGzipEncodingInterceptor feignAcceptGzipEncodingInterceptor() {
        return new FeignAcceptGzipEncodingInterceptor();
    }
}
