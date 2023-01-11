package com.blog.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.api.entities.User;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.UserDto;
import com.blog.api.repositories.UserRepo;
import com.blog.api.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelmapper;

//	@Override
//	public List<UserDto> createAllUser(UserDto userDto) {
//		List<User> user = (List<User>) this.dtoToUser(userDto);
//		List<User> savedUser = this.userRepo.saveAll(user);
//		return (List<UserDto>) this.userToDto((User) savedUser);
//
//	}

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);

		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user2 = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		user2.setName(userDto.getName());
		user2.setEmail(userDto.getEmail());
		user2.setPassword(userDto.getPassword());
		user2.setAbout(userDto.getAbout());

		User updatedUser = this.userRepo.save(user2);
		UserDto userDto1 = this.userToDto(updatedUser);

		return userDto1;

	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.userRepo.findAll();

		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		this.userRepo.delete(user);

	}

	public User dtoToUser(UserDto dto) {

		// User user = new User();
		User user2 = this.modelmapper.map(dto, User.class);

//
//		user.setId(dto.getId());
//		user.setName(dto.getName());
//		user.setEmail(dto.getEmail());
//		user.setPassword(dto.getPassword());
//		user.setAbout(dto.getAbout());
		return user2;

	}

	public UserDto userToDto(User user) {

		UserDto dto = this.modelmapper.map(user, UserDto.class);

//		dto.setId(user.getId());
//		dto.setName(user.getName());
//		dto.setEmail(user.getEmail());
//		dto.setAbout(user.getAbout());
//		dto.setPassword(user.getPassword());
		return dto;

	}
//
//	public List<User> dtoToUser(List<UserDto> dto) {
//
//		// User user = new User();
//		List<User> user2 = (List<User>) this.modelmapper.map(dto, User.class);
//
////
////		user.setId(dto.getId());
////		user.setName(dto.getName());
////		user.setEmail(dto.getEmail());
////		user.setPassword(dto.getPassword());
////		user.setAbout(dto.getAbout());
//		return user2;
//
//	}
//
//	public List<UserDto> userToDto(List<User> user) {
//
//		List<UserDto> dto = (List<UserDto>) this.modelmapper.map(user, UserDto.class);
//
////		dto.setId(user.getId());
////		dto.setName(user.getName());
////		dto.setEmail(user.getEmail());
////		dto.setAbout(user.getAbout());
////		dto.setPassword(user.getPassword());
//		return dto;
//
//	}

	@Override
	public List<User> insertAllUsers(List<User> userList) {
		return userRepo.saveAll(userList);
	}

	@Override
	public List<User> insertAllUser(List<User> userList) {
		return userRepo.saveAll(userList);
	}

}
