package com.softvider.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import javax.inject.Inject;
import java.util.List;

@Configuration
@SuppressWarnings("deprecation")
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Inject private AccessDeniedHandler accessDeniedHandler;
    @Inject private AuthenticationEntryPoint authenticationEntryPoint;

    private static final List<String> ANONYMOUS_REQUESTS = List.of(
            "/anonymous/**"
    );

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .stateless(true)
                .accessDeniedHandler(this.accessDeniedHandler)
                .authenticationEntryPoint(this.authenticationEntryPoint);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(ANONYMOUS_REQUESTS.toArray(String[]::new))
                    .permitAll()
                .anyRequest()
                    .authenticated()
                    .and()
                .exceptionHandling()
                    .accessDeniedHandler(this.accessDeniedHandler)
                    .authenticationEntryPoint(this.authenticationEntryPoint);;
    }

}
