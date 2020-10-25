package com.calendar.service;

import java.util.List;
import java.util.Map;

import com.calendar.entities.Events2;
import com.calendar.entities.User;

public interface UserService {

	public abstract User addUser(User user);
	
	public abstract User findUserByEmail(String email);
	public abstract User findByEmailAndPass(String email, String pass);
	public abstract List<Map<String, Object>> getUserProfesionals(int idCentro);
	public abstract User findUserByEmailAndVigente(String email, int vigente);

}
