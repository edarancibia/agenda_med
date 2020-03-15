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
public interface PacienteJpaRepository extends JpaRepository<Paciente, Serializable> {
	
	public abstract Paciente findByDni(int rutnum);

	//@Query("SELECT nombre FROM Paciente WHERE rut_num= :rut_num")
	//Future<String> findByRut(@Param("rut_num") int rut_num);
}
