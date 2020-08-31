package com.calendar.service;

import java.util.List;

import com.calendar.entities.Ficha;

public interface FichaService {

	public abstract Ficha addFicha(Ficha ficha);
	
	public List<Ficha> findFichaByRutPac(int rutPac);
	
	public abstract Ficha findFichaByIdFicha(Long idFicha);
}
