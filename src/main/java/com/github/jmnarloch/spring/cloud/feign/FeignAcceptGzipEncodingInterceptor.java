/**
 * Copyright (c) 2015 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
