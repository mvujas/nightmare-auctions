package com.github.mvujas.nightmareauctionsbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.mvujas.nightmareauctionsbackend.filters.JwtAuthenticationFilter;
import com.github.mvujas.nightmareauctionsbackend.filters.JwtAuthorizationFilter;
import com.github.mvujas.nightmareauctionsbackend.managers.jwt.JwtUsernameManager;
import com.github.mvujas.nightmareauctionsbackend.services.UserService;

@Configuration
@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

	@Autowired 
	private JwtUsernameManager jwtManager;
	
	@Autowired
	private UserService userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/**").permitAll()
			.and()
			.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtManager))
			.addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtManager, userService));
//			.formLogin()
//			.permitAll()
//			.and()
//			.httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
