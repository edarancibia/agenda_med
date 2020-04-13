package com.calendar.impl;

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

}
