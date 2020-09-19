package com.calendar.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.entities.Ficha;
import com.calendar.repository.FichaRepository;
import com.calendar.service.FichaService;

@Service
public class FichaServiceImpl implements FichaService {

	@Autowired
	public FichaRepository fichaRepository;
	
	@Override
	public Ficha addFicha(Ficha ficha) {
		return fichaRepository.save(ficha);
	}

	@Override
	public Ficha findFichaByIdFicha(Long idFicha) {
		return fichaRepository.findFichaByIdFicha(idFicha);
	}

	@Override
	public List<Map<String, Object>> findFichasByRutPac(int rutPac) {
		return fichaRepository.findFichasByRutPac(rutPac);
	}

}
