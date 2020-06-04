package com.example.restaurant.configuraion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/", "/index",
                        "/registration", "/login", "/swagger**",
                        "/webjars/**", "/css/**", "/pic/**",
                        "/static/favicon.ico")
                .permitAll()
//                .antMatchers("/admin/**")
//                .hasRole(UserRole.ADMIN.name())
                .anyRequest().authenticated()
                .and();

        http.exceptionHandling()
                .accessDeniedPage("/access-denied")
                .and();
        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/authorized", true)
                .failureUrl("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .usernameParameter("j_login")
                .passwordParameter("j_password")
                .permitAll()
                .and();
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .invalidateHttpSession(true);
    }


    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
