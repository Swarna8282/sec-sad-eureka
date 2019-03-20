package com.fedex.sad;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:constants.properties")
public class EurekaSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${name}")
	private String sName;
	@Value("${password}")
	private String sPassword;
	@Value("${role}")
	private String sRole;
	
	@Override
	protected void configure (HttpSecurity hSec) throws Exception {		
		hSec.authorizeRequests().antMatchers("/eureka/**").hasRole(sRole)
			.anyRequest().denyAll()
			.and()
			.httpBasic().and().csrf().disable();
	}
	
	@Bean
	public BCryptPasswordEncoder bcpEncoder () {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure (AuthenticationManagerBuilder amBuilder) throws Exception {
		amBuilder.inMemoryAuthentication()
			.withUser(sName).password(bcpEncoder().encode(sPassword)).roles(sRole);
	}

}
