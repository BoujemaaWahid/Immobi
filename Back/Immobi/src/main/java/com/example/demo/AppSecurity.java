package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        //.csrf()
        //.csrfTokenRepository(csrfTokenRepository())
        //.and()
        .authorizeRequests()
        .anyRequest().authenticated()
        .and()
        //.addFilterAfter(new CustomCsrfFilter(), CorsFilter.class)
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
	
	@SuppressWarnings("unused")
	private CsrfTokenRepository csrfTokenRepository() {
		
		CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        tokenRepository.setCookiePath("/");
        tokenRepository.setHeaderName(CustomCsrfFilter.CSRF_COOKIE_NAME);
        return tokenRepository;
	}
}