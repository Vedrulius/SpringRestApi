package com.mihey.springrestapi.config;

import com.mihey.springrestapi.model.Role;
import com.mihey.springrestapi.security.jwt.JwtConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtConfigurer jwtConfigurer;

    public SecurityConfiguration(JwtConfigurer jwtConfigurer) {
        this.jwtConfigurer = jwtConfigurer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/register").permitAll()
                .antMatchers("/api/v1/auth/login").permitAll()
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
                .and().apply(jwtConfigurer);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
