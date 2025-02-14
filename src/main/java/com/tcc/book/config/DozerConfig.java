package com.tcc.book.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class DozerConfig {
    @Bean
    public DozerBeanMapper dozerBeanMapper() {

        List<String> mappingFiles = Collections.singletonList("dozer-config.xml");
        return new DozerBeanMapper(mappingFiles);
    }
}
