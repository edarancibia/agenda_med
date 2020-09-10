package com.calendar.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.calendar.entities.Profesional;

public interface ProfesionalRepository extends JpaRepository<Profesional, Long> {
	
	public abstract Profesional findByEmail(String email);
	
	//lista profesionales para combo
	@Query(value = "select \r\n" + 
			"u.id ,u.email ,CONCAT(u.a_pat ,' ',u.a_mat , ' ', u.nombre ) as prof \r\n" + 
			"FROM usuario_centro uc ,users u \r\n" + 
			"where uc.idCentro = :idCentro and u.id = uc.idUsuario and u.perfil =1", nativeQuery = true)
	List<Map<String, Object>> getProfByCentro(int idCentro);
	
	public abstract Profesional findProfesionalByIdProfesional(Long idProfesional);
	
	public abstract Profesional findProfesionalByFkIdUsuario(int idUsuario);
}
