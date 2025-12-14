package com.rollerspeed.rollerspeed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Roller Speed API")
                        .version("1.0")
                        .description("Documentación de Servicios para la Escuela de Patinaje Roller Speed")
                        .termsOfService("[http://swagger.io/terms/](http://swagger.io/terms/)")
                        .license(new License().name("Apache 2.0").url("[http://springdoc.org](http://springdoc.org)")));
    }
}
