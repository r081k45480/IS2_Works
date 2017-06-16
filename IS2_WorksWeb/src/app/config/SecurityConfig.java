package app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
		
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

	  auth.jdbcAuthentication()
	  .dataSource(dataSource)
	  .passwordEncoder(passwordEncoder())
		.usersByUsernameQuery(
			"select username, password, 1 from user where username=?")
		.authoritiesByUsernameQuery(
			"select username,uloga from user where username=?");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

	  http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
			.formLogin();/*.loginPage("login")
			.failureUrl("login?error")
			.usernameParameter("username")
			.passwordParameter("password")
	  .and()
	  	.logout().logoutSuccessUrl("login")
	  .and()
	  	.csrf();*/
	  

	}
}
