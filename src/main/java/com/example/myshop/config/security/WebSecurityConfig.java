package com.example.myshop.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String AUTHCOOKIENAME = "AUTH";

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT member_id, password, 'true' FROM member WHERE member_id = ?" )
                .authoritiesByUsernameQuery("SELECT member_id, authority FROM member_authorities WHERE member_id = ?")
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/vendor/**",
                "/api/**",
                "/images/**",
                "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.securityContext().securityContextRepository(new CookieSecurityContextRepository(userDetailsService()));
        http.requestCache().requestCache(new NullRequestCache());

        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/categories/**", "/products/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin() // login
                .loginPage("/login")
                .permitAll()
                .successHandler(new CustomAuthSuccessHandler())
                .and()
                .logout() // /login?logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/loggedOut")
                .deleteCookies(AUTHCOOKIENAME)
                .permitAll()
                .and()
                .csrf().disable()
        ;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }
}
