package ru.konsist.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationProperties(prefix = "app")
@ConfigurationPropertiesScan
@Data
@AllArgsConstructor
public class AppProperties {
    private String jenkinsHost;
    private String jenkinsPort;
}
