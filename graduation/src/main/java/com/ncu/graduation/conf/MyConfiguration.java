package com.ncu.graduation.conf;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author ：grh
 * @date ：Created in 2020/4/18 16:07
 * @description：字符编码
 * @modified By：
 * @version: 0.0.1
 */
@Configuration
public class MyConfiguration {

  @Bean
  public HttpMessageConverters customConverters() {
    FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    fastJsonConfig.setCharset(Charset.forName(StandardCharsets.UTF_8.name()));
    fastJsonConverter.setFastJsonConfig(fastJsonConfig);
    HttpMessageConverter<?> additional = fastJsonConverter;

    return new HttpMessageConverters(additional);
  }

}
