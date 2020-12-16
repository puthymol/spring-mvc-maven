package com.softvider.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


@Configuration
@SuppressWarnings("deprecation")
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "resource-server-rest-api";
    private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
    private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
    private static final String SECURED_PATTERN = "/secured/**";
//    private static final List<String> POST_ANONYMOUS_REQUESTS = List.of(
//            "/client/register",
//            "/otp/anonymous/**",
//            "/ckfinder/**"
//    );
//    private static final List<String> GET_ANONYMOUS_REQUESTS = List.of(
//            "/location/**",
//            "/user/verify/**",
//            "/news-and-article/**",
//            "/key-value/payment-step",
//            "/key-value/about-us",
//            "/key-value/contact-us",
//            "/ckfinder/**"
//    );

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }

//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//
//        http.headers().frameOptions().sameOrigin();
//        http.antMatcher("/**").authorizeRequests()
//                .antMatchers("/health", "/swagger-resources/**", "/v2/**", "/admin/**", "/resources/**", "/files/**").permitAll()
//                .antMatchers(POST_ANONYMOUS_REQUESTS.toArray(String[]::new)).permitAll()
//                .antMatchers(HttpMethod.GET, GET_ANONYMOUS_REQUESTS.toArray(String[]::new)).permitAll()
//                .anyRequest().authenticated();
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin/**").authenticated()
                .anyRequest().authenticated();
    }
}
