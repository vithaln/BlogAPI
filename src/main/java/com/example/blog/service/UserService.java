package com.example.blog.service;

import java.util.List;

import com.example.blog.entities.User;
import com.example.blog.payloads.UserDto;

public interface UserService {
	
	UserDto createUserDto(UserDto user);
	UserDto update(UserDto userDto, Integer userid);
	UserDto getuserbyid(Integer userid);
	List<UserDto> getAlluser();
	void deleteuser(Integer userid);
	
	//feignClient
	//List<UserDto> getAlluserByUsingFeignClients();
	
}
