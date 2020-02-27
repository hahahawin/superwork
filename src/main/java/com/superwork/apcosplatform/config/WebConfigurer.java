package com.superwork.apcosplatform.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {


    @Autowired
    RedisUtil redisUtil;

    //1.这个为解决中文乱码
//    @Bean
//    public HttpMessageConverter<String> responseBodyConverter() {
//        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        return converter;
//    }


    /**
     * @param
     * @return
     * @Description //添加静态资源
     * @author xjj
     * @date 14:18
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
            registry.addResourceHandler("/data/**").addResourceLocations("file:G:/data/");
        } else {  //linux 和mac
            registry.addResourceHandler("/data/**").addResourceLocations("file:/data/");
        }
//        registry.addResourceHandler("/temporary/**").addResourceLocations("file:F:/temporary/");
//        registry.addResourceHandler(staticAccessPath+"**").addResourceLocations(uploadFolder.split(","));
    }

    /**
     * 跨域支持
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600 * 24);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor(redisUtil)).addPathPatterns("/**").excludePathPatterns("/","/index/**","/css/**","/js/**","/fonts/**",
                "/img/**","/bootstrap/**","/html/**","/interface/**","/swagger-ui.html","/webjars/**","/swagger-resources/**","/v2/api-docs","/appUnLogin/**","/upload/**","/device/**","/data/**","/thirdInterface/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
    }

    /**
     * BigDecimal Long 转化为String
     * @return
     */
    @Bean
    public MappingJackson2HttpMessageConverter toStringConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(BigDecimal.class, BigDecimalToStringSerializer.instance);
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(long.class, ToStringSerializer.instance);
        mapper.registerModule(simpleModule);
        converter.setObjectMapper(mapper);
        return converter;
    }

    @JacksonStdImpl
    static class BigDecimalToStringSerializer extends ToStringSerializer {
        public final static BigDecimalToStringSerializer instance = new BigDecimalToStringSerializer();

        public BigDecimalToStringSerializer() {
            super(Object.class);
        }

        public BigDecimalToStringSerializer(Class<?> handledType) {
            super(handledType);
        }

        @Override
        public boolean isEmpty(SerializerProvider prov, Object value) {
            if (value == null) {
                return true;
            }
            String str = ((BigDecimal) value).stripTrailingZeros().toPlainString();
            return str.isEmpty();
        }

        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider provider)
                throws IOException {
            gen.writeString(((BigDecimal) value).stripTrailingZeros().toPlainString());
        }

        @Override
        public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
            return createSchemaNode("string", true);
        }

        @Override
        public void serializeWithType(Object value, JsonGenerator gen,
                                      SerializerProvider provider, TypeSerializer typeSer)
                throws IOException {
            // no type info, just regular serialization
            serialize(value, gen, provider);
        }
    }


}
