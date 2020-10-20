package com.gpro.consulting.tiers.commun.rest.elementBase.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.commun.coordination.login.value.RechercheMulticritereUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.ResultatRechecheUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.RoleValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;
import com.gpro.consulting.tiers.commun.rest.security.AccountService;
import com.gpro.consulting.tiers.commun.rest.security.AuthenticatedUser;
import com.gpro.consulting.tiers.commun.rest.security.JWTAuthenticationFilter;
import com.gpro.consulting.tiers.commun.rest.security.JwtUtil;
import com.gpro.consulting.tiers.commun.service.login.IRoleService;
import com.gpro.consulting.tiers.commun.service.login.IUserService;

/**
 * User Login Controller
 * 
 * @author Wahid Gazzah
 * @since 03/06/2016
 *
 */

@RestController
@RequestMapping("/user")
public class UserRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(UserRestImpl.class);
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private JWTAuthenticationFilter auth;
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheUserValue rechercherMultiCritere(@RequestBody RechercheMulticritereUserValue request) {
		 
		//logger.info("rechercheMulticritere: Delegating request to service layer.");

		return userService.rechercherMultiCritere(request);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public AuthenticatedUser login(@RequestBody UserValue request) {
		 
		//logger.info("login: Delegating request to service layer.");
		if( userService.login(request) != null)
			{
			UserValue user = userService.findByUsername(request.getUserName());

			String token =accountService.generateToken(request.getUserName());
//			UserValue user = userService.findByUsername(request.getUserName());
	        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoleNames().toString());
	return new AuthenticatedUser(user.getId(), user.getUserName(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getJob(), token, authorityList,user.getRoles());
			}
		else
			return null;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody UserValue user) {
		
		//logger.info("createUser: Delegating request to Service layer.");
		
	    Collection<RoleValue> roles= new ArrayList<RoleValue>();
		
		for(String code :user.getListRoles() ) {
			
			RoleValue role = roleService.getByCode(code);
			
			
			if(role != null)
				roles.add(role);
			
			
		}
		
		user.setRoles(roles);
		
		user.setPassword(user.getNewPassword());
		
		return accountService.createUserWithCryptPassword(user);
	}
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(ModelMap model) {
//        return "login";
//    }
// 
//    @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
//    public String loginerror(ModelMap model) {
//        model.addAttribute("error", "true");
//        return "denied";
//    }
// 
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout(ModelMap model) {
//        return "logout";
//    }
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String save() {
		
		//logger.info("createUser: Delegating request to Service layer.");
		
		 accountService.saveUser("gpro", "2020", "2020","Admin");
		 return "ok";
		 }
	
	
	@RequestMapping(value = "/createUserWithCryptPassword", method = RequestMethod.POST)
	public String save2(@RequestBody UserValue request) {
		
		//logger.info("createUser: Delegating request to Service layer.");
		
		
		 accountService.createUserWithCryptPassword(request);
		 
		 return "ok";
		 }
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public UserValue getById(@PathVariable Long id) {
		  
		UserValue user =  userService.getById(id);
		user.setNewPassword(user.getPassword());
		
		Set<String> listRoles= new HashSet<String>();
		
		for(RoleValue role : user.getRoles())
			listRoles.add(role.getCode());
		
		
		
		
		
		user.setListRoles(listRoles);
		 return user;
	}
	
	
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody UserValue user) {
		
		Collection<RoleValue> roles= new ArrayList<RoleValue>();
		
		for(String code :user.getListRoles() ) {
			
			RoleValue role = roleService.getByCode(code);
			
			
			if(role != null)
				roles.add(role);
			
			
		}
		
		user.setRoles(roles);
	    
		//logger.info("UpdateUser: Delegating request to service layer.");
		
		if(isNotEmty(user.getPassword()) && isNotEmty(user.getNewPassword()))
		{
			
			if(!user.getPassword().equals(user.getNewPassword())) {
				user.setPassword(user.getNewPassword());
				
				return accountService.updateUserWithCryptPassword(user);
			}else
			{
				return userService.update(user);
			}
				
			
		}
	
		
		return null;
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//logger.info("deleteUser: Delegating request id {} to service layer.", id);
		  
		userService.delete(id);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<UserValue> getAll() {
		  
		//logger.info("getAllUser: Delegating request id to service layer.");
		
		return userService.getAll();
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public JwtUtil getJwtUtil() {
		return jwtUtil;
	}

	public void setJwtUtil(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	public JWTAuthenticationFilter getAuth() {
		return auth;
	}

	public void setAuth(JWTAuthenticationFilter auth) {
		this.auth = auth;
	}
	private boolean isNotEmty(String value) {
		return value != null && !"".equals(value) && !value.equals("undefined") && !value.equals("null");
	}
	
}
