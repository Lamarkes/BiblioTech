package com.example.library.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//ModelMapper biblioteca de mapeamento de objetos Spring

// Bean utilziada para realizar o mapeamento dos campos da entidade RequestUpdateBookDTO para a entidade Book
//economizando codigo e fazendo uma atualização mais limpa

// será implementado na camada de Service
@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
