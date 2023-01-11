package com.blog.api.services;

import java.util.List;

import com.blog.api.entities.User;
import com.blog.api.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);

	List<User> insertAllUsers(List<User> userList);
	
	List<User> insertAllUser(List<User> userList);

}
