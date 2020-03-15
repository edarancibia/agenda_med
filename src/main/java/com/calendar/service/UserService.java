package com.calendar.service;

import java.util.List;

import com.calendar.entities.Events2;
import com.calendar.entities.User;

public interface UserService {

	public abstract User addUser(User user);
	
	public abstract User findUserByEmail(String email);
	public abstract User findByEmailAndPass(String email, String pass);

}
