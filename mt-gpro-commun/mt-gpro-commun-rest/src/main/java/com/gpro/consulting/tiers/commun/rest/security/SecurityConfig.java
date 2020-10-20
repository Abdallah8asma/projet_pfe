//package com.gpro.consulting.tiers.commun.rest.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.ImportResource;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	 public SecurityConfig() {
//	      super();
//	   }
//	@Autowired
//	private UserDetailsService userDetailsService;
//	
////	@Autowired
////	private BCryptPasswordEncoder bCryptPasswordEncoder;
//	@Bean
//	BCryptPasswordEncoder getBCPE(){
//		
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth)
//			throws Exception {
//	
//		auth.userDetailsService(userDetailsService).passwordEncoder(getBCPE());
//
//
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//        
//		 http.csrf().disable();
//
//		 http.authorizeRequests().antMatchers("**/login/**","**/inscription/**").permitAll();
//		 http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login");
////		http.formLogin();
////		 http.authorizeRequests().antMatchers("/user/getAll/").hasAuthority("ENSEIGNANT");
////		 http.authorizeRequests().anyRequest().authenticated();
////		 http.csrf().disable()
////			.authorizeRequests().antMatchers("/").permitAll()
////			.anyRequest().authenticated()
////			.and().formLogin().loginPage("/login").defaultSuccessUrl("/welcome").failureUrl("/login?error=true").permitAll()
////			.and().logout().deleteCookies("JSESSIONID").logoutUrl("/logout").logoutSuccessUrl("/login"); 
//		 /*http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		 http.authorizeRequests().antMatchers("/login/**","/register/**").permitAll();
//		 http.authorizeRequests().antMatchers("/user/**").hasAuthority("USER");
//		 http.authorizeRequests().antMatchers("/matieres/**").hasAuthority("ENSEIGNANT");
//		 http.authorizeRequests().antMatchers("/commentaires/**").hasAuthority("ADMIN");
//		 http.authorizeRequests().anyRequest().authenticated();*/
////		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
////        http.addFilterBefore(new JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
//
//	}
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//}
