package com.calendar.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.calendar.entities.UsuarioCentro;

public interface UsuarioCentroRepository extends JpaRepository<UsuarioCentro, Long> {

	//public abstract UsuarioCentro addUsuarioCentro(long fk_idUsuario,long fk_idCentro);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="insert into usuario_centro (idUsuario,idCentro) values(:idUsuario,:idCentro)", nativeQuery=true)
	void addUsuarioCentro(@Param("idUsuario")long idUsuario,@Param("idCentro") long idCentro);
	
	public abstract UsuarioCentro findByIdUsuario(long idUsuario);
}
