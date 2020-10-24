package com.calendar.repository;

import java.util.List;
import java.util.Map;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.calendar.entities.Clinica;


public interface ClinicaJpaRepository extends JpaRepository<Clinica, Long> {

	@Query(value = "select c.id as idCentro,c.nombreClinica as nombreCmed\n" + 
			"from usuario_centro uc, cmed c \n" + 
			"where uc.idCentro = c.id and c.id = :idCmed and uc.idUsuario = :idUser", nativeQuery = true)
	List<Map<String, Object>> getClinicaByUser(int idUser, long idCmed);
	
	public abstract Clinica findClinicaById(Long idClinica);
	
}
