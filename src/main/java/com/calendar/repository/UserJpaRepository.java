package com.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendar.entities.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	User findByEmailAndPass(String email, String pass);

}
