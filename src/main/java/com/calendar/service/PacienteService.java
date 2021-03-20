package com.calendar.service;

import java.util.List;
import java.util.Map;

import com.calendar.entities.Paciente;

public interface PacienteService {

	public abstract Paciente addPaciente(Paciente paciente);
	
	List<Map<String, Object>> obtienePorRut(int rutnum);
	
	Map<String, Object> getPacienteByFicha(int idficha);
}
