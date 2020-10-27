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
	private Long id;
	
	@Column(name = "idUsuario")
	private Long idUsuario;
	
	@Column(name = "idCentro")
	private Long idCentro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(Long idCentro) {
		this.idCentro = idCentro;
	}

	public UsuarioCentro(Long id, Long idUsuario, Long idCentro) {
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
