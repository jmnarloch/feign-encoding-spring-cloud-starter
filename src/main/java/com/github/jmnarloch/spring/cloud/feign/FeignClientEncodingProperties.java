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

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The Feign encoding properties.
 *
 * @author Jakub Narloch
 */
@ConfigurationProperties("feign.compression")
public class FeignClientEncodingProperties {

    /**
     * The list of supported mime types.
     */
    private String[] mimeTypes = new String[]{"text/xml", "application/xml", "application/json"};

    /**
     * The minimum threshold content size.
     */
    private int minRequestSize = 2048;

    /**
     * Retrieves the mime types.
     *
     * @return the mime types
     */
    public String[] getMimeTypes() {
        return mimeTypes;
    }

    /**
     * Sets the mime types
     *
     * @param mimeTypes the mime types
     */
    public void setMimeTypes(String[] mimeTypes) {
        this.mimeTypes = mimeTypes;
    }

    /**
     * Retrieves the minimum response size.
     *
     * @return the minimum response size
     */
    public int getMinRequestSize() {
        return minRequestSize;
    }

    /**
     * Sets the minimum response size.
     *
     * @param minRequestSize the minimum response size
     */
    public void setMinRequestSize(int minRequestSize) {
        this.minRequestSize = minRequestSize;
    }
}
