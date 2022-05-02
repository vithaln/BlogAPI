package com.example.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotNull
	@NotEmpty
	@Size(min=4,max=10,message="username must be at least 4 characters and maximum 10!!")
	private String name;
	
	@NotNull
	@NotEmpty
	//@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
	//@Size(min=4,max=10,message="passsword must be at least 4 characters and maximum 10!!")
	private String password;

	@Email
	@Size(message="Please enter valid Email adress!!")
	private String email;
	
	@NotNull
	@NotEmpty
	//@Size(min=5,max=12,message="please enter about must be 5 to 12 chars!!")
	private String about;
}
