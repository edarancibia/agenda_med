package com.calendar.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
		return clinicaRepository.save(clinica);
	}

	@Override
	public List<Map<String, Object>> getClinicaByUser(int idUser, long idCmed) {
		return clinicaRepository.getClinicaByUser(idUser, idCmed);
	}

	@Override
	public Clinica findClinicaById(Long idClinica) {
		return clinicaRepository.findClinicaById(idClinica);
	}

}
