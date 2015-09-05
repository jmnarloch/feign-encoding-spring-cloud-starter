package com.github.jmnarloch.spring.cloud.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures the Feign request compression.
 *
 * @author Jakub Narloch
 * @see FeignContentGzipEncodingInterceptor
 */
@Configuration
public class FeignContentGzipEncodingConfiguration {

    @Bean
    public FeignContentGzipEncodingInterceptor feignContentGzipEncodingInterceptor() {
        return new FeignContentGzipEncodingInterceptor();
    }
}
