package com.practice.blog.backend.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.blog.backend.exception.ResourceNotFoundException;
import com.practice.blog.backend.model.User;
import com.practice.blog.backend.payload.UserDto;
import com.practice.blog.backend.repo.UserRepo;
import com.practice.blog.backend.service.UserService;

@Service
public class userServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto user) {
		User modeluser=this.dtoToEntity(user);
		User savedUser=this.userRepo.save(modeluser);
		return this.modelToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		User modelUser=this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		
		modelUser.setName(user.getPassword());
		modelUser.setEmail(user.getEmail());
		modelUser.setPassword(user.getPassword());
		modelUser.setAbout(user.getAbout());
		User updatedUser=this.userRepo.save(modelUser);
		return this.modelToDto(updatedUser);
	}

	@Override
	public UserDto getUser(Integer userId) {
		User modelUser=this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		return this.modelToDto(modelUser);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users=this.userRepo.findAll();
		List<UserDto> users2= users.stream().map(user->this.modelToDto(user)).collect(Collectors.toList());
		return users2;
	}

	@Override
	public String deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		this.userRepo.delete(user);
		return "User deleted with id"+userId;
		
	}
	
	private User dtoToEntity(UserDto user) {
		User userob=this.modelMapper.map(user, User.class);
//		User userobj=new User();		
//		userobj.setId(user.getId());
//		userobj.setName(user.getName());
//		userobj.setEmail(user.getEmail());
//		userobj.setAbout(user.getAbout());
//		userobj.setPassword(user.getPassword());
		
		return userob;
	}
	
	private UserDto modelToDto(User user) {
		UserDto userob=this.modelMapper.map(user, UserDto.class);
//		UserDto userobj=new UserDto();
//		userobj.setId(user.getId());
//		userobj.setName(user.getName());
//		userobj.setEmail(user.getEmail());
//		userobj.setAbout(user.getAbout());
//		userobj.setPassword(user.getPassword());
//		
		return userob;
	}


}
