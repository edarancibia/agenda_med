package com.calendar.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cmed")
public class Clinica {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="nombreclinica")
	private String nombreClinica;
	
	@Column(name="direccionclinica")
	private String direccionClinica;
	
	@Column(name="telefonoclinica")
	private String telefonoClinica;
	
	@Column(name="pais")
	private String pais;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreClinica() {
		return nombreClinica;
	}

	public void setNombreClinica(String nombreClinica) {
		this.nombreClinica = nombreClinica;
	}

	public String getDireccionClinica() {
		return direccionClinica;
	}

	public void setDireccionClinica(String direccionClinica) {
		this.direccionClinica = direccionClinica;
	}

	public String getTelefonoClinica() {
		return telefonoClinica;
	}

	public void setTelefonoClinica(String telefonoClinica) {
		this.telefonoClinica = telefonoClinica;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Clinica(Long id, String nombreClinica, String direccionClinica, String telefonoClinica, String pais) {
		this.id = id;
		this.nombreClinica = nombreClinica;
		this.direccionClinica = direccionClinica;
		this.telefonoClinica = telefonoClinica;
		this.pais = pais;
	}

	public Clinica() {
		
	}

	@Override
	public String toString() {
		return "Clinica [id=" + id + ", nombreClinica=" + nombreClinica + ", direccionClinica=" + direccionClinica + ", telefono="
				+ telefonoClinica +  ", pais=" + pais + "]";
	}
	
	
}
