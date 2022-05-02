package com.example.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.UserDto;
import com.example.blog.service.UserService;

@RestController
//@RequestMapping("api/users")
public class UserController {
	
	Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userservice;

	// post-create user
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userdto) {

		UserDto createUserDto = this.userservice.createUserDto(userdto);

		logger.info("============InputRequest"+createUserDto);
		
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

	}

	// put-byId
	@PutMapping("/{userid}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userdto, @PathVariable("userid") Integer uid)
	// public ResponseEntity<UserDto> updateUser(@PathVariable Integer userid)
	{

		UserDto Updateduser = this.userservice.update(userdto, uid);
		return ResponseEntity.ok(Updateduser);

	}
//Delete-user

	@DeleteMapping("/{userid}")
	public ResponseEntity<UserDto> DeleUser(@PathVariable Integer userid) {

		this.userservice.deleteuser(userid);

		return new ResponseEntity(new ApiResponse("user Deleted Successfully", true), HttpStatus.OK);

	}

	// Get-user
	@GetMapping("/getAllusers")
	public ResponseEntity<List<UserDto>> getAllUsers() {

		return ResponseEntity.ok(this.userservice.getAlluser());

	}

	// Get user By userid
	@GetMapping("/{userid}")
	public ResponseEntity<UserDto> getuserById(@PathVariable Integer userid) {

		return ResponseEntity.ok(this.userservice.getuserbyid(userid));

	}
	
	

}
