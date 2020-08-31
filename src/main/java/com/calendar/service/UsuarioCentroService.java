package com.calendar.service;

import com.calendar.entities.UsuarioCentro;

public interface UsuarioCentroService {

	public abstract void addUsuarioCentro(long idUsuario,long idCentro);
	
	public abstract UsuarioCentro findByIdUsuario(long idUsuario);
}
