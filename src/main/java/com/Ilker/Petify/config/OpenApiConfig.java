package com.Ilker.Petify.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
/*@SecurityScheme(
        name = "bearerAuth",
        description = "JwtAuth desc",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)*/
public class OpenApiConfig {

    @Bean
    public OpenAPI baseAPI(@Value("${application-description}")String description,
                           @Value("${application-version}") String version){
        return new OpenAPI()
                .info(new Info()
                        .title("Petify")
                        .description(description)
                        .version(version)
                        .contact(new Contact()
                                .name("Me")
                                .email("ilkerkubilaykarabiyik@gmail.com")));
    }
}
