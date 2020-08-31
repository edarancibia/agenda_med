package com.calendar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendar.entities.Ficha;

@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {

	public List<Ficha> findFichaByRutPac(int rutPac);
	
	public abstract Ficha findFichaByIdFicha(Long idFicha);
}
