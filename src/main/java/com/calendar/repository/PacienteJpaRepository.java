package com.calendar.repository;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calendar.entities.Paciente;

@Repository("pacienteRepository")
public interface PacienteJpaRepository extends JpaRepository<Paciente, Long> {
	
	@Query(value = "select YEAR(CURRENT_TIMESTAMP) - YEAR(p.fecha_nac ) - (RIGHT(CURRENT_TIMESTAMP, p.fecha_nac ) < RIGHT(p.fecha_nac , 5)) as age, \n"
			+ " p.idpaciente,TIMESTAMPDIFF(YEAR,p.fecha_nac ,CURDATE()) as edad, p.dni,UPPER(p.a_pat) as a_pat,UPPER(p.a_mat) as a_mat,UPPER(p.nombre) as nombre, \n" + 
			"p.email,p.telefono,p.direccion, DATE_FORMAT(p.fecha_nac,'%d-%m-%Y') as fecha_nac ,p.sexo \n" + 
			"from paciente p  where p.dni = :rutnum", nativeQuery = true)
	public abstract Paciente getByRut(int rutnum);

}
