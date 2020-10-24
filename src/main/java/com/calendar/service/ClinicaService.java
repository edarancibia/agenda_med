package com.calendar.service;

import java.util.List;
import java.util.Map;

import com.calendar.entities.Clinica;

public interface ClinicaService {

	public abstract Clinica addClinica(Clinica clinica);
	
	List<Map<String, Object>> getClinicaByUser(int idUser, long idCmed);
	
	public abstract Clinica findClinicaById(Long idClinica);

}