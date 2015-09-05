# Spring Cloud Netflix Feign content encoding

> A Spring Cloud Feign extension for request/response compression.

[![Build Status](https://travis-ci.org/jmnarloch/feign-encoding-spring-cloud-starter.svg?branch=master)](https://travis-ci.org/jmnarloch/feign-encoding-spring-cloud-starter)

## Features

Enables the request and response GZIP compression. 

## Setup

Add the Spring Cloud starter to your project:

```xml
<dependency>
  <groupId>com.github.jmnarloch</groupId>
  <artifactId>feign-encoding-spring-cloud-starter</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Usage

To hint the server that client supports GZIP content encoded responses add `@EnableFeignAcceptGzipEncoding`

```java
@EnableFeignAcceptGzipEncoding
@EnableFeignClients
@Configuration
public static class Application {

}
```

## Server side setup

### Spring Boot 1.2.x

As long as you are using Spring Boot 1.2.x enabling GZIP compression varies depending on which embedded server you use:
http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#how-to-enable-http-response-compression

For Tomcat you need to specify fallowing properties:

```yaml
server:
  tomcat:
    compression: 1024
    compressableMimeTypes: application/xml,application/json
```

If you use Undertow or Jetty you can use Jetty's GzipFilter instead. Although be aware that the GzipFilter has been 
removed from jetty-servlets somewhere around version 9.3+

### Spring Boot 1.3+

Due to the removal of the Jetty GzipFilter Spring Boot 1.3 will bring it's own internal GZIP support:

https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-1.3.0-M2-Release-Notes

The new properties will work with any underlying application server.

```yaml
server:
  compression:
    enabled: true
    min-response-size: 1024
    mime-types: application/xml,application/json
```

## License

Apache 2.0