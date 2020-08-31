package com.calendar.impl;

import java.util.List;

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
	public List<Ficha> findFichaByRutPac(int rutPac) {
		return fichaRepository.findFichaByRutPac(rutPac);
	}

	@Override
	public Ficha findFichaByIdFicha(Long idFicha) {
		return fichaRepository.findFichaByIdFicha(idFicha);
	}

}
