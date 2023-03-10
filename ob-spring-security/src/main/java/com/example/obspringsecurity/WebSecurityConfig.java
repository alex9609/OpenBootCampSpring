package com.example.obspringsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/hola").permitAll()  //en antMatchers -> Le pasamos una expresion ej: un url con determinada forma
                //y con permitAll permitimos las peticiones, esto se puede replicar por cada url
                .anyRequest().authenticated() // Aquí le decimos que todas las peticiones tienen que estár autenticadas
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }
}
