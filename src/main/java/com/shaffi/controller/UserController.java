package com.shaffi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaffi.entity.User;
import com.shaffi.entity.UserLogin;
import com.shaffi.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@PostMapping("/registerUser")
	public User registerUsers(@RequestBody User user) throws Exception {
        
		String tempUser=user.getUserName();
		
		if(tempUser!=null && !"".equals(tempUser)) {
		 User userobj=userService.fetchByUserName(tempUser);
		 if(userobj!=null) {
			 throw new Exception("user with "+user.getUserName()+ "is already exist"); 
		 }
		}
		return userService.registerUsers(user); 
	}
	@PostMapping("/login")
	
	public User loginUser(@RequestBody UserLogin user) throws Exception {
		
		String tempUserName=user.getUserName();
		String tempPassword=user.getPassword();
		User userObj=null;
		if(tempUserName!=null&&tempPassword!=null) {
			 userObj=userService.fetchByUserNameAndPassword(tempUserName,tempPassword);
		}
		if(userObj==null) {
			throw new Exception("Bad Creadential");
		}
		return userObj;
		
	}
	
	
}
