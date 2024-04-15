//package com.gpro.consulting.tiers.commun.rest.security;
//
//import java.util.ArrayList; 
//import java.util.Collection;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.gpro.consulting.tiers.commun.coordination.login.value.RoleValue;
//import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;
//
//@Service 
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//    
//	@Autowired
//	private AccountService accountService;
//	 //private AccountService 
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//UserValue user=	accountService.loadUserByUsername(username);
//		if(user==null) throw new UsernameNotFoundException("invalid user ");
//		Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
//		
//       for(RoleValue r: user.getRoles())
//	{  authorities.add(new SimpleGrantedAuthority(r.getDesignation()));
////System.out.println(r.getRoleName());
//	}
//       return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities) ;
//}
//}
