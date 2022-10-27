package com.employeeinfo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.employeeinfo.domain.UserRegistrationDto;
import com.employeeinfo.entity.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
