package com.github.jmnarloch.spring.cloud.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Enables the HTTP request payload compression by specifying the {@code Content-Encoding} headers.
 *
 * @author Jakub Narloch
 */
public class FeignContentGzipEncodingInterceptor implements RequestInterceptor {

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(RequestTemplate template) {

        template.header(HttpEncoding.CONTENT_ENCODING_HEADER, HttpEncoding.GZIP_ENCODING, HttpEncoding.DEFLATE_ENCODING);
    }
}
