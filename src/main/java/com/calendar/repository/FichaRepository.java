package com.calendar.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.calendar.entities.Ficha;

@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {

	//public List<Ficha> findFichaByRutPac(int rutPac);
	
	@Query(value = "select f.idficha, f.rut_pac ,f.peso ,f.estatura ,f.antecedentes ,f.motivo ,f.diagnostico ,f.indicaciones,\n" + 
			"f.examen_fis ,f.sol_exam ,f.idprofesional ,DATE_FORMAT(f.fecha,'%d-%m-%Y') as fecha\n" + 
			"from ficha f \n" + 
			"where f.rut_pac = :rutPac",nativeQuery = true)
	List<Map<String, Object>> findFichasByRutPac(int rutPac);
	
	public abstract Ficha findFichaByIdFicha(Long idFicha);
}
