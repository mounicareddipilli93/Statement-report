package com.assignment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * SpringSecurityConfig to provide authentication and authorities
 * 
 * @author Rajesh Majji
 * @version 1.0
 * @since 31/08/2020
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String ADMIN="ADMIN";
	private static final String USER="USER";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("testUser").password("{noop}userpassword").roles(USER)
                .and()
                .withUser("testadmin").password("{noop}adminpassword").roles(USER,ADMIN);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http         
           .authorizeRequests()
            .antMatchers("/resources/**", "/webjars/**","/assets/**").permitAll()
               .antMatchers("/").permitAll()
               .antMatchers(HttpMethod.GET, "/statement/statement-report/**").hasRole(USER)
               .antMatchers(HttpMethod.GET, "/statement/statement-report-amountrange/**").hasRole(ADMIN)
               .antMatchers("/get-statement").hasRole(USER)
               .antMatchers("/get-statement-daterange").hasRole(ADMIN)
               .antMatchers("/get-statement-amountrange").hasRole(ADMIN)
               .anyRequest().authenticated()
               .and()
           .formLogin()
               .loginPage("/login")
               .defaultSuccessUrl("/index")
               .failureUrl("/login?error")
               .permitAll()
               .and()
               .logout()
                   .invalidateHttpSession(true)
                   .clearAuthentication(true)
                   .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                   .logoutSuccessUrl("/login?logout")
                       .permitAll()
           .and()
               .rememberMe()
                   .key("unique-and-secret")
                   .rememberMeCookieName("remember-me-cookie-name")
                   .tokenValiditySeconds(86400)
                   .and()
    	.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        	.and()
    	.sessionManagement().maximumSessions(1)
    	.and()
    	.invalidSessionUrl("/?sessionexpired=true");
    }

}