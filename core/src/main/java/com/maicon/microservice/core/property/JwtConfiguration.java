package com.maicon.microservice.core.property;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "jwt.config")
public class JwtConfiguration {

    private String loginUrl = "/login/**";

    @NestedConfigurationProperty
    private Header header = new Header();

    private int expiration = 3600;
    private String privateKey = "987654321";
    private String type = "encrypted";

    @Getter
    @Setter
    private static class Header {
        private String name = "Authorization";
        private String prefix = "Bearer ";
    }
}
