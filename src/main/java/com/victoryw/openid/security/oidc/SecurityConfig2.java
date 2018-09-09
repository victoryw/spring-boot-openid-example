package com.victoryw.openid.security.oidc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig1 {

    @Configuration
    @Order(2)
    public static class Other2ServiceConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            OtherAuthenticationTokenFilter filter = new OtherAuthenticationTokenFilter();


            http.antMatcher("/sso/sso2/**").
                    authorizeRequests().
                    anyRequest().permitAll();

            http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
            http.headers().cacheControl();
        }
    }

    @Configuration
    @Order(3)
    public static class OtherServiceSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            OtherAuthenticationTokenFilter filter = new OtherAuthenticationTokenFilter();


            http.antMatcher("/sso/sso2/**").authorizeRequests().anyRequest().permitAll().and().
                    antMatcher("/sso/**").
                        authorizeRequests().
                        anyRequest()
                        .hasAuthority("B");

            http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
            http.headers().cacheControl();
        }
    }


    @Configuration
    @Order(1)
    public static class OidcServiceSecurityConfig extends WebSecurityConfigurerAdapter {

        private final CustomAuthenticationProvider authProvider;

        private final EntryPointUnauthorizedHandler unauthorizedHandler;

        @Autowired
        public OidcServiceSecurityConfig(CustomAuthenticationProvider authProvider, EntryPointUnauthorizedHandler unauthorizedHandler) {
            this.authProvider = authProvider;
            this.unauthorizedHandler = unauthorizedHandler;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authProvider);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
        OidcAuthenticationTokenFilter filter =
                new OidcAuthenticationTokenFilter("/oidc-restricted/**");
        filter.setAuthenticationManager(this.authenticationManagerBean());

            http.antMatcher("/oidc-restricted/**")
                    .authorizeRequests().anyRequest().hasAuthority("a");
            http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
            http.headers().cacheControl();
        }

    }
}
