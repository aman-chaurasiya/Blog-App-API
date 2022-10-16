package com.codewithaman.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	@NotEmpty
	//@Size(min=4,message="Username must be min of 4 char..")
	private String name;
	@NotEmpty
	//@Email(message = "Email is not valid...")
	private String email;
	@NotEmpty
	//@Size(min=3,max=10,message = "password should be min of 3 char and max of 10 char...")
	private String password;
	@NotEmpty
	private String about;

}
