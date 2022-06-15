package com.vinisolon.fullstackcourse.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinisolon.fullstackcourse.domain.PagamentoBoleto;
import com.vinisolon.fullstackcourse.domain.PagamentoCartao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(PagamentoBoleto.class);
                objectMapper.registerSubtypes(PagamentoCartao.class);
                super.configure(objectMapper);
            }
        };
    }

}
