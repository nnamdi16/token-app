package com.nnamdi.validator.config;

import com.nnamdi.validator.utils.PropertySourceResolver;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig {
    PropertySourceResolver propertySourceResolver;


    public SwaggerConfig(PropertySourceResolver propertySourceResolver) {
        this.propertySourceResolver = propertySourceResolver;
    }

    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.email("nwabuokeinnamdi19@gmail.com");
        contact.name("Nwabuokei Nnamdi");

        Info info = new Info()
                .title(propertySourceResolver.getProjectName())
                .version(propertySourceResolver.getProjectVersion())
                .contact(contact)
                .description(propertySourceResolver.getProjectDescription());
        return new OpenAPI().info(info);

    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}
