package org.cs320.ozyegin.config;

import org.cs320.ozyegin.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService getDetailsService() {
		return new CustomUserDetailsService();
	}



	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/", "/register", "/signIn", "/saveUser", "/marketplace", "static/css/**", "/userLogin").permitAll()
				.requestMatchers("/user/**","sellProduct", "saveImage").authenticated().and()
				.formLogin().loginPage("/signIn").loginProcessingUrl("/userLogin")
				.defaultSuccessUrl("/user/home").permitAll();
		return http.build();
	}

}