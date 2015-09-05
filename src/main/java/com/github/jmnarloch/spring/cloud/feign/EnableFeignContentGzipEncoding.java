package com.github.jmnarloch.spring.cloud.feign;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Enables the content encoding of the request payloads. This is accomplish by specifying the {@code Content-Encoding}
 * HTTP header on the client side.
 *
 * Use with caution, the server must be configured to support GZIP / Deflate compression in order to process the
 * incoming request.
 *
 * @author Jakub Narloch
 * @see FeignAcceptGzipEncodingInterceptor
 * @see FeignAcceptGzipEncodingConfiguration
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(EnableFeignContentGzipEncoding.class)
public @interface EnableFeignContentGzipEncoding {
}
