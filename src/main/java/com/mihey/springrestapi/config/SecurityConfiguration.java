package com.mihey.springrestapi.config;

import com.mihey.springrestapi.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/register").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/regions").authenticated()
                .antMatchers(HttpMethod.POST, "/api/v1/regions").hasAnyAuthority(Role.ADMIN.name(), Role.MODERATOR.name())
                .antMatchers(HttpMethod.PUT, "/api/v1/regions").hasAnyAuthority(Role.ADMIN.name(), Role.MODERATOR.name())
                .antMatchers(HttpMethod.DELETE, "/api/v1/regions").hasAnyAuthority(Role.ADMIN.name(), Role.MODERATOR.name())
                .antMatchers(HttpMethod.GET, "/api/v1/posts").authenticated()
                .antMatchers(HttpMethod.POST, "/api/v1/posts").hasAnyAuthority(Role.ADMIN.name(), Role.MODERATOR.name())
                .antMatchers(HttpMethod.PUT, "/api/v1/posts").hasAnyAuthority(Role.ADMIN.name(), Role.MODERATOR.name())
                .antMatchers(HttpMethod.DELETE, "/api/v1/posts").hasAnyAuthority(Role.ADMIN.name(), Role.MODERATOR.name())
                .antMatchers(HttpMethod.GET, "/api/v1/writers").authenticated()
                .antMatchers(HttpMethod.POST, "/api/v1/writers").hasAnyAuthority(Role.ADMIN.name(), Role.MODERATOR.name())
                .antMatchers(HttpMethod.PUT, "/api/v1/writers").hasAnyAuthority(Role.ADMIN.name(), Role.MODERATOR.name())
                .antMatchers(HttpMethod.DELETE, "/api/v1/writers").hasAnyAuthority(Role.ADMIN.name(), Role.MODERATOR.name())
                .antMatchers(HttpMethod.GET, "/api/v1/users").hasAnyAuthority(Role.ADMIN.name(), Role.MODERATOR.name())
                .antMatchers(HttpMethod.POST, "/api/v1/users").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/api/v1/users").hasAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/api/v1/users").hasAuthority(Role.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and().httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
