package com.example.springbootsecurity.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().csrfTokenRepository(new CookieCsrfTokenRepository()).requireCsrfProtectionMatcher(
                new RequestMatcher() {
                    @Override
                    public boolean matches(HttpServletRequest httpServletRequest) {
                        return httpServletRequest.getMethod().equals("POST");
                        //&& httpServletRequest.getRequestURI(  ).startsWith("/login");
                    }
                }
        );


        //  CSP
        http.headers().contentSecurityPolicy("script-src https://code.jquery.com/");


        //X-Frame-Options header
        http.headers().frameOptions().sameOrigin();

        // XSS header
        http.headers().xssProtection().block(true);


        //授权

        http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin()
                .loginPage("/login").failureForwardUrl("/error").permitAll()
                .and().logout().permitAll();

    }


    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {


        authenticationManagerBuilder.inMemoryAuthentication().withUser("zzz").password("123456").roles("ADMIN", "USER")
                .and().withUser("lll").password("123456").roles("ADMIN");


    }

}
