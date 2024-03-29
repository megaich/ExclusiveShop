package com.meg.shoppingcart.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/**").permitAll()
				.anyRequest().fullyAuthenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/", true).failureUrl("/login?error").permitAll()
				.and().logout().logoutUrl("/logout").permitAll();
		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withUsername("user")
				.password("{noop}pass")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}
