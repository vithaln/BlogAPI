package com.example.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.blog.repository.UserRepo;

@SpringBootTest
class BlogApplicationTests {
	
	@Autowired
	private UserRepo userrepo;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void RepoTest() {
		
	String RepoClass=	this.userrepo.getClass().getName();
	Package RepoPackage=	this.userrepo.getClass().getPackage();
	System.out.println("Class Name is : "+RepoClass);
	System.out.println("Package name is : "+RepoPackage);
		
	}

}
