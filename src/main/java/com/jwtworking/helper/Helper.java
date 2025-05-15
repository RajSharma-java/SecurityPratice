package com.jwtworking.helper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Helper
{
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

}
