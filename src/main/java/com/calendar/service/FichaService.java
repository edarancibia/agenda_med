package com.calendar.service;

import java.util.List;
import java.util.Map;

import com.calendar.entities.Ficha;

public interface FichaService {

	public abstract Ficha addFicha(Ficha ficha);
	
	List<Map<String, Object>> findFichasByRutPac(int rutPac);
	
	public abstract Ficha findFichaByIdFicha(Long idFicha);
}
