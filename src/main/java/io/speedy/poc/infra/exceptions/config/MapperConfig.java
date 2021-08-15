package io.speedy.poc.infra.exceptions.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class MapperUtil {
    /**
     * Model mapper.
     */
    protected ModelMapper modelMapper;

    /**
     * Default Constructor.
     */
    @Bean
    public MapperUtil() {
        this.modelMapper = new ModelMapper();
    }
}
