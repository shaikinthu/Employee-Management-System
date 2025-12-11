package com.example.jpa.security;

import javax.sql.DataSource;

//import java.beans.Customizer;

//import java.beans.Customizer;

//import org.apache.catalina.User;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class DemoSecurityConfig {

	@Bean
	UserDetailsManager userDetailsManager(DataSource dataSource) {
		
		JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);
		
		jdbcUserDetailsManager.setUsersByUsernameQuery("select userid,pwd,active from members where userid=?");
		
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select userid,role from roles where userid=?");

		
		return  jdbcUserDetailsManager;
		
	}
	
	
    @Bean 
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    	 http.authorizeHttpRequests(configurer -> configurer
    			 .requestMatchers(HttpMethod.GET,"/list").hasRole("EMPLOYEE")
    			 .requestMatchers(HttpMethod.POST,"/save").hasRole("EMPLOYEE")
//    			 .requestMatchers(HttpMethod.POST,"/showFormForAdd").hasRole("MANAGER")
    			 .requestMatchers(HttpMethod.GET,"/showFormForAdd").hasRole("MANAGER")
    			 .requestMatchers(HttpMethod.GET,"/showFormForUpdate").hasRole("MANAGER")
    			 .requestMatchers(HttpMethod.GET,"/delete").hasRole("ADMIN")
//    			 .requestMatchers(HttpMethod.DELETE,"/delete").hasRole("ADMIN")
    			 );
    	 http.httpBasic(Customizer.withDefaults());
    	 
    	 http.csrf(csrf->csrf.disable());
    	 return http.build();
     }

}
