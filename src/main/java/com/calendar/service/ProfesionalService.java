package com.calendar.service;

import com.calendar.entities.Profesional;

public interface ProfesionalService {

	public abstract Profesional addProfesional(Profesional profesional);
	
	public abstract Profesional findByEmail(String email);
}
