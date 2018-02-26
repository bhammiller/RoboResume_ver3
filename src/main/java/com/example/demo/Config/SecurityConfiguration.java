package com.example.demo.Config;

import com.example.demo.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUDS(appUserRepository);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .antMatchers("/","/register","/contact","/css/**","/js/**","/addcover","/rlist","/addorg","/processorg","/orgdetail/{id}",
                        "/addjobskill","/processjobskill","/jobdetail/{id}","/processjob","/addjob","/joblist","/2.jpg").permitAll()
                .antMatchers("/h2-console/**","/updatecontact/{id}","/process","/processeducation","/updateeducation/{id}",
                        "/deleteeducation/{id}","/processexp","/updateexp/{id}","/deleteexp/{id}","/processskill",
                        "/updateskill/{id}","/deleteskill/{id}","/processreferals","/updateref/{id}","/deleteref/{id}","/processcover",
                        "/addreferals","/updatecover/{id}","/deletecover/{id}","/processsummary","/updatesummary/{id}",
                        "/deletesummary/{id}","/addeducation","/addexp","/addskills","/addsummary","/complete"
                        ).hasAuthority("APPLICANT")
                .anyRequest().authenticated();

        http
                .formLogin().failureUrl("/login?error")
                .defaultSuccessUrl("/loggedin")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceBean());
    }
}
