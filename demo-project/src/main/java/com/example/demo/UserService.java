package com.example.demo;

public interface UserService {

	AdminTable saveUser(AdminTable admin);
	AdminTable getUser(Long id);
	boolean userExists(String email, String mobile);
	boolean validateLogin(String email, String rawPassword);
	
}
