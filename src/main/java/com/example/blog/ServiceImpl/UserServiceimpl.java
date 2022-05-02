package com.example.blog.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entities.Post;
import com.example.blog.entities.User;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.payloads.PostDto;
import com.example.blog.payloads.UserDto;
import com.example.blog.repository.UserRepo;
import com.example.blog.service.UserService;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelmapper;

	@Override
	public UserDto createUserDto(UserDto userDto) {

		User user = this.DtoToUser(userDto);
		User savedUser = userRepo.save(user);

		return this.UserToDto(savedUser);
	}

	@Override
	public UserDto update(UserDto userDto, Integer userid) {

		User user = this.userRepo.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("user", "Id", userid));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());

		User Updateuser = this.userRepo.save(user);
		UserDto updaUserDto = this.UserToDto(Updateuser);

		return updaUserDto;
	}

	@Override
	public UserDto getuserbyid(Integer userid) {

		User user = this.userRepo.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userid));

		return this.UserToDto(user);
	}

	@Override
	public List<UserDto> getAlluser() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteuser(Integer userid) {
		User user = this.userRepo.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", userid));

		this.userRepo.delete(user);

	}

	public User DtoToUser(UserDto userDto) {

		User user = this.modelmapper.map(userDto, User.class);

		// User user =new User(); //without modelmapper

//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
//		
		return user;

	}

	public UserDto UserToDto(User user) {

		UserDto userDto = this.modelmapper.map(user, UserDto.class);

//	UserDto userDto=new UserDto();
//		
//
//	userDto.setId(user.getId());
//	userDto.setName(user.getName());
//	userDto.setEmail(user.getEmail());
//	userDto.setPassword(user.getPassword());
//	userDto.setAbout(user.getAbout());

		return userDto;

	}
/////////////

}
