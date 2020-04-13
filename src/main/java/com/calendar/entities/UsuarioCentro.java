package com.calendar.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_centro")
public class UsuarioCentro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuarioCentro;
	
	@Column(name = "idUsuario")
	private long fk_idUsuario;
	
	@Column(name = "idCentro")
	private long fk_idCentro;

	public long getIdUsuarioCentro() {
		return idUsuarioCentro;
	}

	public void setIdUsuarioCentro(long idUsuarioCentro) {
		this.idUsuarioCentro = idUsuarioCentro;
	}

	public long getFk_idUsuario() {
		return fk_idUsuario;
	}

	public void setFk_idUsuario(long fk_idUsuario) {
		this.fk_idUsuario = fk_idUsuario;
	}

	public long getFk_idCentro() {
		return fk_idCentro;
	}

	public void setFk_idCentro(long fk_idCentro) {
		this.fk_idCentro = fk_idCentro;
	}

	public UsuarioCentro(long idUsuarioCentro, long fk_idUsuario, long fk_idCentro) {
		
		this.idUsuarioCentro = idUsuarioCentro;
		this.fk_idUsuario = fk_idUsuario;
		this.fk_idCentro = fk_idCentro;
	}

	public UsuarioCentro() {
		
	}

	@Override
	public String toString() {
		return "UsuarioCentro [idUsuarioCentro=" + idUsuarioCentro + ", fk_idUsuario=" + fk_idUsuario + ", fk_idCentro="
				+ fk_idCentro + "]";
	}
	
	
	
	
}
