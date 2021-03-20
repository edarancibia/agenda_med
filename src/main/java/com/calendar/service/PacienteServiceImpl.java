package com.calendar.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.calendar.entities.Paciente;
import com.calendar.repository.PacienteRepository;

@Service
public class PacienteServiceImpl implements PacienteService {

	@Autowired
	public PacienteRepository pacienteRepository;

	@Override
	public Paciente addPaciente(Paciente paciente) {
		return pacienteRepository.save(paciente);
	}

	@Override
	public List<Map<String, Object>> obtienePorRut(int rutnum) {
		return pacienteRepository.obtienePorRut(rutnum);
	}

	@Override
	public Map<String, Object> getPacienteByFicha(int idficha) {
		return pacienteRepository.getPacienteByFicha(idficha);
	}

}
