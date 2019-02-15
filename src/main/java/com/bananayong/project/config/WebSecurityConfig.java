package com.bananayong.project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig { // NOSONAR

    @Configuration
    public static class ApiWebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                .authorizeRequests()
                    .mvcMatchers("/hello").permitAll()
                    .anyRequest().hasRole("USER")
                    .and()
                .httpBasic()
                    .disable()
                .formLogin()
                    .disable()
                .logout().permitAll()
                    .and()
                .csrf()
                    .disable();
            // @formatter:on
        }
    }

    @Order(1)
    @Configuration
    @RequiredArgsConstructor
    public static class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {

        private static final String ACTUATOR_ROLE = "ADMIN";
        private final SecurityProperties securityProperties;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http.requestMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeRequests()
				.requestMatchers(
						EndpointRequest.to(HealthEndpoint.class, InfoEndpoint.class)).permitAll()
                .anyRequest()
                    .hasRole(ACTUATOR_ROLE)
                    .and()
                .formLogin()
                    .and()
				.httpBasic();
            // @formatter:on
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            var user = securityProperties.getUser();
            auth
                .inMemoryAuthentication()
                    .withUser(user.getName())
                        .password(user.getPassword())
                        .roles(ACTUATOR_ROLE);
        }
    }
}
