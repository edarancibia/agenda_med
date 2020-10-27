package com.calendar.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calendar.entities.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	User findByEmailAndPass(String email, String pass);
	
	@Query(value = "select \n" + 
			"u.id,\n" + 
			"concat(u.a_pat,' ',u.a_mat,' ',u.nombre) as nombreProfesional\n" + 
			"from users u, profesional p\n" + 
			"where u.perfil = 1 and u.id = p.fk_idUsuario and p.cod_centro=:idCentro", nativeQuery = true)
	List<Map<String, Object>> getUserProfesionals(@Param("idCentro") int idCentro);

	public abstract User findUserByEmailAndVigente(String email, int vigente);
	
	//LISTA DE USUARIOS PARA PANEL DE ADMINISTRACION
	@Query("select concat(u.a_pat,' ',u.a_mat,' ',u.nombre) as nombre,\n" + 
			"if(u.perfil = 1,'Atención Profesional','Agenda') as perfil\n" + 
			"from users u, usuario_centro uc\n" + 
			"where u.id = uc.idUsuario and uc.idCentro = :idCentro")
	List<Map<String, Object>> getListaUsuariosCentro(int idCentro);
}
