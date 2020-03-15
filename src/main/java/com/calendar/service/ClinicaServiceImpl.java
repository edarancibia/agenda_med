package com.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.calendar.entities.Clinica;
import com.calendar.repository.ClinicaJpaRepository;

@Service("clinicaServiveImpl")
public class ClinicaServiceImpl implements ClinicaService {

	@Autowired
	@Qualifier("clinicaJpaRepository")
	private ClinicaJpaRepository clinicaRepository;
	
	@Override
	public Clinica addClinica(Clinica clinica) {
		Clinica clinica_ = clinicaRepository.save(clinica);
		return clinica_;
	}

}
