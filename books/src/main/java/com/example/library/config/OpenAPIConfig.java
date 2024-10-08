package com.example.library.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {



    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API for BiblioTech")
                        .version("1.0")
                        .description("Documentação das rotas para o sistema Bibliotech")
                        .termsOfService("")
                        .license(new License()
                                .name("")
                                .url("")));
    }
}
