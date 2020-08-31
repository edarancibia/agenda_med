package com.calendar.service;

import java.util.List;
import java.util.Map;

import com.calendar.entities.Profesional;

public interface ProfesionalService {

	public abstract Profesional addProfesional(Profesional profesional);
	
	public abstract Profesional findByEmail(String email);
	
	public abstract Profesional findProfesionalByIdProfesional(Long idProfesional);
	
	List<Map<String, Object>> getProfByCentro(int idCentro);
}
