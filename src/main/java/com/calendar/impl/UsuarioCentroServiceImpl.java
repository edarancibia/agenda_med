package com.calendar.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.entities.UsuarioCentro;
import com.calendar.repository.UsuarioCentroRepository;
import com.calendar.service.UsuarioCentroService;

@Service("usuarioCentroServiceImpl")
public class UsuarioCentroServiceImpl implements UsuarioCentroService {

	@Autowired
	private UsuarioCentroRepository usuarioCentroRepo;
	
	@Override
	public  void addUsuarioCentro(long idUsuario, long idCentro) {
		 usuarioCentroRepo.addUsuarioCentro(idUsuario, idCentro);
	}

}
