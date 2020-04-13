package com.calendar.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.calendar.entities.Profesional;

public interface ProfesionalRepository extends JpaRepository<Profesional, Long> {

	/*@Transactional
	@Modifying
	@Query(value="insert into profesional (rut,nombre,a_pat,a_mat,direccion,email,cod_centro) "
			+ "values(:rut,:nombre,:a_pat,:a_mat,:cod_centro)", nativeQuery=true)
	void addProfesional(@Param("txtclinica")String txtclinica,@Param("txtDireccion") String txtDireccion);*/
	
	public abstract Profesional findByEmail(String email);
}
