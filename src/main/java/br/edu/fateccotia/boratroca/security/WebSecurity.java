package br.edu.fateccotia.boratroca.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurity {
	@Autowired
	private FilterToken filter;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/home").permitAll()
                .requestMatchers(HttpMethod.GET, "/usuario/cadastrar").permitAll()
                .requestMatchers(HttpMethod.POST, "/usuario/cadastrar").permitAll()
                .requestMatchers(HttpMethod.POST, "/usuario/logar").permitAll()
                .requestMatchers(HttpMethod.GET, "/usuario/logar").permitAll()
                .anyRequest().authenticated().and()
                
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager
		(AuthenticationConfiguration authenticationConfiguration) throws Exception {

		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
