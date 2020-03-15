package com.calendar.service;

import com.calendar.entities.Paciente;

public interface PacienteService {

	public abstract Paciente findByDni (int rutnum);
	public abstract Paciente addPaciente(Paciente paciente);
}
