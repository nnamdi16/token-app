package com.nnamdi.generator.config;

import com.nnamdi.generator.utils.PropertySourceResolver;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
