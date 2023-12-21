package org.cs320.ozyegin;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Comment out or revert the specific lines causing issues
                //.authorizeRequests()
                //    .antMatchers("/profile").authenticated()
                //    .anyRequest().permitAll()
                //    .and()
                //.formLogin()
                //    .loginPage("/login")
                //    .permitAll()
                //    .successHandler((request, response, authentication) -> {
                //        response.setStatus(200);
                //    })
                //    .failureHandler((request, response, exception) -> {
                //        response.setStatus(401);
                //    })
                //    .and()
                //.logout()
                //    .logoutSuccessHandler((request, response, authentication) -> {
                //        response.setStatus(200);
                //    })
                //    .permitAll()
                //    .and()
                .csrf().disable(); // Disable CSRF for simplicity (not recommended for production)
    }
}
