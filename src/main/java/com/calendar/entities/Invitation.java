package com.calendar.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invitaciones")
public class Invitation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idInvitacion")
	private Long idInvitacion;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "fk_idUsuario")
	private int fk_idUsuario;
	
	@Column(name = "fk_idClinica")
	private int fk_idClinica;
	
	@Column(name = "perfil")
	private int perfil;

	public Long getIdInvitacion() {
		return idInvitacion;
	}

	public void setIdInvitacion(Long idInvitacion) {
		this.idInvitacion = idInvitacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getFk_idUsuario() {
		return fk_idUsuario;
	}

	public void setFk_idUsuario(int fk_idUsuario) {
		this.fk_idUsuario = fk_idUsuario;
	}

	public int getFk_idClinica() {
		return fk_idClinica;
	}

	public void setFk_idClinica(int fk_idClinica) {
		this.fk_idClinica = fk_idClinica;
	}

	public int getPerfil() {
		return perfil;
	}

	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}

	public Invitation(Long idInvitacion, String email, int fk_idUsuario, int fk_idClinica, int perfil) {
		
		this.idInvitacion = idInvitacion;
		this.email = email;
		this.fk_idUsuario = fk_idUsuario;
		this.fk_idClinica = fk_idClinica;
		this.perfil = perfil;
	}

	public Invitation() {
		
	}

	@Override
	public String toString() {
		return "Invitation [idInvitacion=" + idInvitacion + ", email=" + email + ", fk_idUsuario=" + fk_idUsuario
				+ ", fk_idClinica=" + fk_idClinica + ", perfil=" + perfil + "]";
	}
	
	
}
