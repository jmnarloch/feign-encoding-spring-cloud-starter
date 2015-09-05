package com.github.jmnarloch.spring.cloud.feign;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Enables receiving GZIP/Deflate compressed responses by sending in response header {@code Accept-Encoding}
 * {@code gzip} {@code deflate} as supported encoding types. Though, this not yet implies that the response
 * will be compressed and requires the server to be configured for compressing the output responses.
 *
 * @author Jakub Narloch
 * @see FeignAcceptGzipEncodingInterceptor
 * @see FeignAcceptGzipEncodingConfiguration
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(FeignAcceptGzipEncodingConfiguration.class)
public @interface EnableFeignAcceptGzipEncoding {
}
