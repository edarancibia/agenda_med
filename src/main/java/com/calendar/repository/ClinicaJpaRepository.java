package com.calendar.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.calendar.entities.Clinica;


public interface ClinicaJpaRepository extends JpaRepository<Clinica, Long> {

	

	
}
