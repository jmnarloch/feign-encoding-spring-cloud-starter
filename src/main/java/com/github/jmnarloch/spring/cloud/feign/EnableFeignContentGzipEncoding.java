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
@Import(FeignContentGzipEncodingConfiguration.class)
public @interface EnableFeignContentGzipEncoding {
}
