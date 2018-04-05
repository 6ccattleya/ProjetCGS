package dev.cgs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class ConfigSec extends WebSecurityConfigurerAdapter {
	/**
	 *@author <a href="https://www.github.com/6ccattleya"> <b>bamba cisse </b> </a> <br>
	 * Pour plus d'information sur l'erreur <b> There is no PasswordEncoder mapped for the id “null”</b> <a href="https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-encoding">Clic ici </a>
	 * 
	 **/
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser(User.withUsername("user").password("{noop}user").roles("USER").build());
		auth.inMemoryAuthentication()
		 .withUser(User.withUsername("mara").password("{noop}mara").roles("ADMIN").build());
	}

	public void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
			.antMatchers("/css/**", "/js/**", "/pp/**")
				.permitAll()
					.anyRequest().authenticated()
						.and()
		.formLogin()
			.loginPage("/login")
				.defaultSuccessUrl("/index.html")
					.failureUrl("/login")
						.permitAll()
							.and()
		.logout()
			.invalidateHttpSession(true)
				.logoutUrl("/logout")
					.permitAll();

	}
}
