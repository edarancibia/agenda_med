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
	@Column(name = "id")
	private long id;
	
	@Column(name = "idUsuario")
	private long idUsuario;
	
	@Column(name = "idCentro")
	private long idCentro;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public long getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(long idCentro) {
		this.idCentro = idCentro;
	}

	public UsuarioCentro(long id, long idUsuario, long idCentro) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.idCentro = idCentro;
	}

	public UsuarioCentro() {

	}

	@Override
	public String toString() {
		return "UsuarioCentro [id=" + id + ", idUsuario=" + idUsuario + ", idCentro="
				+ idCentro + "]";
	}
	
}
