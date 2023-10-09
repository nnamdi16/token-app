package com.nnamdi.generator.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@AllArgsConstructor
public class PropertySourceResolver {
    @Value("${project.version}")
    private String projectVersion;

    @Value("${project.name}")
    private String projectName;

    @Value("${project.description}")
    private String projectDescription;
}
