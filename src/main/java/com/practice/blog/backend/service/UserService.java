package com.practice.blog.backend.service;

import java.util.List;

import com.practice.blog.backend.payload.UserDto;

public interface UserService {
	
	 UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUser(Integer userId);
	List<UserDto> getAllUser();
	String deleteUser(Integer userId);

}
