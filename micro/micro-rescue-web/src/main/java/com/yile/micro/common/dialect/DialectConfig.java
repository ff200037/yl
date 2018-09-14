package com.yile.micro.common.dialect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class DialectConfig {  
    @Bean
    public ImportSourceDialect getDialect(){
        return new ImportSourceDialect();
    }

} 
