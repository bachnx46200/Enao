package dev.hunghh.springsecurityjwtmysql.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .cors() // Ngăn chặn request từ một domain khác
                .and()
       .authorizeRequests().antMatchers("/login").permitAll().and()
       .authorizeRequests().antMatchers("/register").permitAll()
        .and()
                .authorizeRequests()
                .antMatchers("/api/v4/acc").hasAuthority("USER_READ") // (1)
                .antMatchers("/api/v3").hasAnyAuthority("USER_READ", "USER_CREATE") // (2)
                .anyRequest().authenticated() // (3)
                .and()
                .httpBasic();


    }
}
