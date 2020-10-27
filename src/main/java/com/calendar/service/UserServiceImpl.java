package com.calendar.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.calendar.entities.Events2;
import com.calendar.entities.User;
import com.calendar.repository.UserJpaRepository;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userRepository;
	
	@Override
	public User addUser(User user) {
		User user_ = userRepository.save(user);
		return user_;
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByEmailAndPass(String email, String pass) {
		return userRepository.findByEmailAndPass(email, pass);
	}

	@Override
	public List<Map<String, Object>> getUserProfesionals(Long idCentro) {
		return userRepository.getUserProfesionals(idCentro);
	}

	@Override
	public User findUserByEmailAndVigente(String email, int vigente) {
		return userRepository.findUserByEmailAndVigente(email, vigente);
	}

	@Override
	public List<Map<String, Object>> getListaUsuariosCentro(Long idCentro) {
		return userRepository.getListaUsuariosCentro(idCentro);
	}

	
}
