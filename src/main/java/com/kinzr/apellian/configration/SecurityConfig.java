package com.kinzr.apellian.configration;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.java.Log;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		String queryUser = "";
		String queryAuth = "";
		
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(queryUser)
		.rolePrefix("ROLE")
		.authoritiesByUsernameQuery(queryAuth);
		
		super.configure(auth);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		//log.info("Security config...");
		http.csrf().disable();
		http.authorizeRequests()
        // 페이지 권한 설정
        
		//.antMatchers("/admin/**").hasRole("ADMIN")
        //.antMatchers("/user/myinfo").hasRole("MEMBER")
		
        .antMatchers("/**").permitAll()
        //.antMatchers("/css/**", "/js/**", "/font/**", "/img/**").permitAll()
        //.antMatchers("/static/**").permitAll()

        .and() // 로그인 설정
        .formLogin()
//        .loginPage("/user/login")
//        .defaultSuccessUrl("/user/login/result")
        .permitAll()
//        
//        .and() // 로그아웃 설정
//    	.logout()
//        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
//        .logoutSuccessUrl("/user/logout/result")
//        .invalidateHttpSession(true)
        
        .and()  // 403 예외처리 핸들링
        .exceptionHandling().accessDeniedPage("/user/denied");
		
		super.configure(http);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return rawPassword.equals(encodedPassword);
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}
		};
	}
}

