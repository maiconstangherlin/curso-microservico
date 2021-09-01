package com.maicon.microservice.auth.security.config;

import com.maicon.microservice.auth.security.filter.JwtUsernameAndPasswordAuthenticationFilter;
import com.maicon.microservice.core.property.JwtConfiguration;
import com.maicon.microservice.token.security.config.SecurityTokenConfig;
import com.maicon.microservice.token.security.token.creator.TokenCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityCredentialsConfig extends SecurityTokenConfig {

    private final UserDetailsService userDetailsService;
    private final TokenCreator tokenCreator;
    private final JwtConfiguration jwtConfiguration;

    public SecurityCredentialsConfig(JwtConfiguration jwtConfiguration,
                                     UserDetailsService userDetailsService,
                                     TokenCreator tokenCreator) {
        super(jwtConfiguration);
        this.jwtConfiguration = jwtConfiguration;
        this.userDetailsService = userDetailsService;
        this.tokenCreator = tokenCreator;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfiguration, tokenCreator));
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
