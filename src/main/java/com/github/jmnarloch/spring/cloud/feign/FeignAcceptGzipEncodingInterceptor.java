package com.github.jmnarloch.spring.cloud.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Enables the HTTP response payload compression by specifying the {@code Accept-Encoding} headers.
 * Although this does not yet mean that the requests will be compressed, it requires the remote server
 * to understand the header and be configured to compress responses. Still no all responses might be compressed
 * based on the media type matching and other factors like the response content length.
 *
 * @author Jakub Narloch
 */
public class FeignAcceptGzipEncodingInterceptor implements RequestInterceptor {

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(RequestTemplate template) {

        template.header(HttpEncoding.ACCEPT_ENCODING_HEADER, HttpEncoding.GZIP_ENCODING, HttpEncoding.DEFLATE_ENCODING);
    }
}
