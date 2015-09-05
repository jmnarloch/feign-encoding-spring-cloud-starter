# Spring Cloud Netflix Feign content encoding

> A Spring Cloud Feign extension for request/response compression.

[![Build Status](https://travis-ci.org/jmnarloch/feign-encoding-spring-cloud-starter.svg?branch=master)](https://travis-ci.org/jmnarloch/feign-encoding-spring-cloud-starter)

## Features

Enables Feign request and response GZIP compression. 

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

You may want also to compress the request payloads in order to do this annotate your configuration class 
with `@EnableFeignAcceptGzipEncoding`.

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

## Know issues

If you are using Spring Cloud 1.0.3 (Spring Cloud Netlfix 1.0.1) you may experience fallowing error:
https://github.com/spring-cloud/spring-cloud-netflix/issues/489

```
feign.codec.DecodeException: Could not read JSON: Illegal character ((CTRL-CHAR, code 31)): only regular white space (\r, \n, \t) is allowed between tokens
 at [Source: java.io.PushbackInputStream@c6b2dd9; line: 1, column: 2]; nested exception is com.fasterxml.jackson.core.JsonParseException: Illegal character ((CTRL-CHAR, code 31)): only regular white space (\r, \n, \t) is allowed between tokens
 at [Source: java.io.PushbackInputStream@c6b2dd9; line: 1, column: 2]
	at feign.SynchronousMethodHandler.decode(SynchronousMethodHandler.java:150)
	at feign.SynchronousMethodHandler.executeAndDecode(SynchronousMethodHandler.java:118)
	at feign.SynchronousMethodHandler.invoke(SynchronousMethodHandler.java:71)
	at feign.ReflectiveFeign$FeignInvocationHandler.invoke(ReflectiveFeign.java:94)
	at com.sun.proxy.$Proxy64.getInvoices(Unknown Source)
	at com.github.jmnarloch.spring.cloud.feign.FeignAcceptEncodingTest.compressedResponse(FeignAcceptEncodingTest.java:64)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:73)
	at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:82)
	at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:73)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:224)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:83)
	... 40 more
Caused by: org.springframework.http.converter.HttpMessageNotReadableException: Could not read JSON: Illegal character ((CTRL-CHAR, code 31)): only regular white space (\r, \n, \t) is allowed between tokens
 at [Source: java.io.PushbackInputStream@c6b2dd9; line: 1, column: 2]; nested exception is com.fasterxml.jackson.core.JsonParseException: Illegal character ((CTRL-CHAR, code 31)): only regular white space (\r, \n, \t) is allowed between tokens
 at [Source: java.io.PushbackInputStream@c6b2dd9; line: 1, column: 2]
	at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.readJavaType(AbstractJackson2HttpMessageConverter.java:208)
	at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.read(AbstractJackson2HttpMessageConverter.java:200)
	at org.springframework.web.client.HttpMessageConverterExtractor.extractData(HttpMessageConverterExtractor.java:97)
	at org.springframework.cloud.netflix.feign.support.SpringDecoder.decode(SpringDecoder.java:57)
	at org.springframework.cloud.netflix.feign.support.ResponseEntityDecoder.decode(ResponseEntityDecoder.java:40)
	at feign.SynchronousMethodHandler.decode(SynchronousMethodHandler.java:146)
	... 45 more
Caused by: com.fasterxml.jackson.core.JsonParseException: Illegal character ((CTRL-CHAR, code 31)): only regular white space (\r, \n, \t) is allowed between tokens
 at [Source: java.io.PushbackInputStream@c6b2dd9; line: 1, column: 2]
	at com.fasterxml.jackson.core.JsonParser._constructError(JsonParser.java:1419)
	at com.fasterxml.jackson.core.base.ParserMinimalBase._reportError(ParserMinimalBase.java:508)
	at com.fasterxml.jackson.core.base.ParserMinimalBase._throwInvalidSpace(ParserMinimalBase.java:459)
	at com.fasterxml.jackson.core.json.UTF8StreamJsonParser._skipWSOrEnd(UTF8StreamJsonParser.java:2625)
	at com.fasterxml.jackson.core.json.UTF8StreamJsonParser.nextToken(UTF8StreamJsonParser.java:645)
	at com.fasterxml.jackson.databind.ObjectMapper._initForReading(ObjectMapper.java:3105)
	at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:3051)
	at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:2221)
	at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.readJavaType(AbstractJackson2HttpMessageConverter.java:205)
	... 50 more
```

In order to fix that you have to enable Feign Apache HttpClient:

Add this to classes to your component scan packages:

```java
@Configuration
@ConditionalOnClass({Feign.class})
public class FeignApacheHttpClientAutoConfiguration {

    @Configuration
    @ConditionalOnClass(ApacheHttpClient.class)
    public static class HttpClientConfiguration {

        @Autowired(required = false)
        private HttpClient httpClient;

        @Bean
        public Client feignClient() {
            if (httpClient != null) {
                return new ApacheHttpClient(httpClient);
            }
            return new ApacheHttpClient();
        }
    }
}
```

```
@Configuration
@ConditionalOnClass({ ILoadBalancer.class, Feign.class })
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class FeignRibbonClientAutoConfiguration {

    @Configuration
    @ConditionalOnClass(ApacheHttpClient.class)
    public static class HttpClientConfiguration {

        @Autowired(required = false)
        private HttpClient httpClient;

        @Resource(name = "cachingLBClientFactory")
        private LBClientFactory lbClientFactory;

        @Bean
        public Client feignClient() {
            feign.ribbon.RibbonClient.Builder builder = feign.ribbon.RibbonClient.builder();

            if (httpClient != null) {
                builder.delegate(new ApacheHttpClient(httpClient));
            } else {
                builder.delegate(new ApacheHttpClient());
            }

            if (lbClientFactory != null) {
                builder.lbClientFactory(lbClientFactory);
            }

            return builder.build();
        }
    }
}
```

Spring Cloud 1.1 will do this for you automatically.

## License

Apache 2.0