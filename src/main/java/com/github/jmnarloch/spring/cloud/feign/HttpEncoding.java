package com.github.jmnarloch.spring.cloud.feign;

/**
 * Lists all constants used by this component.
 *
 * @author Jakub Narloch
 */
public interface HttpEncoding {

    /**
     * The HTTP Accept-Encoding header.
     */
    String ACCEPT_ENCODING_HEADER = "Accept-Encoding";

    /**
     * The HTTP Content-Encoding header.
     */
    String CONTENT_ENCODING_HEADER = "Content-Encoding";

    /**
     * The GZIP encoding.
     */
    String GZIP_ENCODING = "gzip";

    /**
     * The Deflate encoding.
     */
    String DEFLATE_ENCODING = "deflate";
}
