package com.calendar.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.calendar.entities.Clinica;

public interface ClinicaJpaRepository extends JpaRepository<Clinica, Long> {

	@Transactional
	@Modifying
	@Query(value="insert into cmed (nombre,direccion) values(:txtclinica,:txtDireccion)", nativeQuery=true)
	void addCmed(@Param("txtclinica")String txtclinica,@Param("txtDireccion") String txtDireccion);
}
