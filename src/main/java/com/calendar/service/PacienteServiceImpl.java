package com.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.calendar.entities.Paciente;
import com.calendar.repository.PacienteJpaRepository;

@Service("pacienteServiceImpl")
public class PacienteServiceImpl implements PacienteService {

	@Autowired
	@Qualifier("pacienteRepository")
	private PacienteJpaRepository pacienteRepository;

	@Override
	public Paciente addPaciente(Paciente paciente) {
		return pacienteRepository.save(paciente);
	}

	@Override
	public Paciente getByRut(int rutnum) {
		return pacienteRepository.getByRut(rutnum);
	}


	

}
