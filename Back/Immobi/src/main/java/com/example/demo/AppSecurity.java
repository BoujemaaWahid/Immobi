package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.repositorys.UserRepository;

@Configuration
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		String password = userRepository.findAdmin().getPassword();
		List<UserDetails> userDetailsList = new ArrayList<>();
		userDetailsList.add(User.withUsername("admin").password(bCryptPasswordEncoder.encode(password)).roles("ADMIN").build());
		userDetailsList.add(User.withUsername("immobi").password(bCryptPasswordEncoder.encode("0000")).roles("PASSAGER").build());
		userDetailsList.add(User.withUsername("member").password(bCryptPasswordEncoder.encode("member")).roles("MEMBER").build());
		return new InMemoryUserDetailsManager(userDetailsList);
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(
				"/*/update","/*/delete", "/*/save",
				"/*/*/update","/*/*/delete", "/*/*/save"
				).hasRole("ADMIN");
        http
        .authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .httpBasic();
        http.cors();
        http.csrf().disable();
    }
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/auth/validation");
	}
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(inMemoryUserDetailsManager());
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService( inMemoryUserDetailsManager() );
	}
}