package org.sid.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String USER = "USER";
	private static final String ADMIN = "ADMIN";

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
				.antMatchers("svc/v1/private/accounts/*").hasRole(USER)
				.antMatchers("svc/v1/private/admin/**").hasRole(ADMIN)
			.and()
				.formLogin();
	}
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("philip").password("password").roles(USER)
			.and()
				.withUser("starritt").password("password").roles(USER, ADMIN);
	}
}
