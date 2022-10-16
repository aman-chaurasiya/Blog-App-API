package com.codewithaman.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codewithaman.blog.payloads.ApiResponse;
import com.codewithaman.blog.payloads.UserDto;
import com.codewithaman.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userservice;

	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUserDto = this.userservice.createUser(userDto);
		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(this.userservice.getAllUser());
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userId") Integer userId) {
		UserDto updateUser = this.userservice.updateUser(userDto, userId);
		return ResponseEntity.ok(updateUser);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId) {
		this.userservice.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully", true), HttpStatus.OK);

	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer userId) {
		return ResponseEntity.ok(this.userservice.getUserById(userId));
	}

	@DeleteMapping("/delAll")
	public ResponseEntity<ApiResponse> deleteAllUser() {
		this.userservice.deleteAllUser();
		return new ResponseEntity<ApiResponse>(new ApiResponse("All User Deleted successfully", true), HttpStatus.OK);
	}

}
