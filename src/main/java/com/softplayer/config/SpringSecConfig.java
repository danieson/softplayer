package com.softplayer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/","/swagger-resources").permitAll();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

	//AUTENTICAÇÃO BÁSICA
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//      http
//          .authorizeRequests()
//              // Para qualquer requisição (anyRequest) é preciso estar 
//              // autenticado (authenticated).
//              .anyRequest().authenticated()
//          .and()
//          .httpBasic();
//    }
//
//    
//    @Override
//    public void configure(AuthenticationManagerBuilder builder) throws Exception {
//      builder
//          .inMemoryAuthentication()
//          .withUser("garrincha").password("123")
//              .roles("USER")
//          .and()
//          .withUser("zico").password("123")
//              .roles("USER");
//    }
}