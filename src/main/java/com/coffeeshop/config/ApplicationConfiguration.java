package com.coffeeshop.config;
 
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class ApplicationConfiguration extends WebSecurityConfigurerAdapter {

	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        	auth
	                .inMemoryAuthentication()
	                .withUser("user")
	                .password("password")
	                .roles("USER")
	                .and()
	                .withUser("admin")
	                .password("admin")
	                .roles("USER", "ADMIN");
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        	http.authorizeHttpRequests()
	                .antMatchers("/admin/**").hasRole("ADMIN")
	                .antMatchers("/order/**").hasRole("USER")
	                .and()
	                .csrf().disable()
	                .httpBasic();
	    } 
	    
	    @Bean
		PasswordEncoder getPasswordEncoder() {
			return NoOpPasswordEncoder.getInstance();
		}

}
