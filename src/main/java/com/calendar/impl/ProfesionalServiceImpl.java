package com.calendar.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.calendar.entities.Profesional;
import com.calendar.repository.ProfesionalRepository;
import com.calendar.service.ProfesionalService;

@Service("profesionalServiceImpl")
public class ProfesionalServiceImpl implements ProfesionalService {

	@Autowired
	@Qualifier("profesionalRepository")
	private ProfesionalRepository profesionalRepository;
	
	@Override
	public Profesional addProfesional(Profesional profesional) {
		return profesionalRepository.save(profesional);
	}

	@Override
	public Profesional findByEmail(String email) {
		return profesionalRepository.findByEmail(email);
	}

	@Override
	public List<Map<String, Object>> getProfByCentro(int idCentro) {
		return profesionalRepository.getProfByCentro(idCentro);
	}

	@Override
	public Profesional findProfesionalByIdProfesional(Long idProfesional) {
		return profesionalRepository.findProfesionalByIdProfesional(idProfesional);
	}

	@Override
	public Profesional findProfesionalByFkIdUsuario(int idUsuario) {
		return profesionalRepository.findProfesionalByFkIdUsuario(idUsuario);
	}

}
