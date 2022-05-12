package tn.esprit.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import tn.esprit.services.MyUserDetailsService;
@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Override
protected void configure (AuthenticationManagerBuilder auth)throws Exception{
	auth.userDetailsService(myUserDetailsService);

}
	@Override
protected void configure (HttpSecurity http)throws Exception{
		http.authorizeRequests()
		.antMatchers("/admin").hasAuthority("ADMIN")
		.antMatchers("/employee").hasAuthority("EMPLOYEE")
		.antMatchers("/entreprise").hasAuthority("ENTREPRISE")
		.antMatchers("/home").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/403");
	}
	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
		
	}
/*	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;		
	}
	@Override
protected void configure (AuthenticationManagerBuilder auth)throws Exception{
	auth.authenticationProvider(authenticationProvider());

}	
	@Override
protected void configure (HttpSecurity http)throws Exception{
		http.authorizeRequests()
			.antMatchers("/admin").hasAuthority("ADMIN")
			.antMatchers("/employee").hasAuthority("EMPLOYEE")
			.antMatchers("/entreprise").hasAuthority("ENTREPRISE")
			.antMatchers("/home").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/403")
			;
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}*/
	/*@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Override
protected void configure (AuthenticationManagerBuilder auth)throws Exception{
	auth.userDetailsService(myUserDetailsService);

}
	@Override
protected void configure (HttpSecurity http)throws Exception{
		http.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasRole("USER")
			.and().formLogin();
	}
	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
		
	}*/
}
