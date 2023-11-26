package com.example.LoginAndRegistWeb.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.LoginAndRegistWeb.Entity.User;
import com.example.LoginAndRegistWeb.Entity.UserRegistrationDto;



public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto userRegistrationDto);

}