package com.calendar.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profesional")
public class Profesional {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idprofesional")
	private Long idProfesional;
	
	@Column(name = "fk_idUsuario")
	private int fkIdUsuario;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "a_pat")
	private String a_pat;
	
	@Column(name = "a_mat")
	private String a_mat;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "cod_esp")
	private int cod_esp;
	
	@Column(name = "tipo_ficha")
	private int tipo_ficha;
	
	@Column(name = "cod_centro")
	private Long cod_centro;
	
	@Column(name = "email")
	private String email;

	public Long getIdProfesional() {
		return idProfesional;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIdProfesional(Long idProfesional) {
		this.idProfesional = idProfesional;
	}

	public int getFkIdUsuario() {
		return fkIdUsuario;
	}

	public void setFkIdUsuario(int fkIdUsuario) {
		this.fkIdUsuario = fkIdUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getA_pat() {
		return a_pat;
	}

	public void setA_pat(String a_pat) {
		this.a_pat = a_pat;
	}

	public String getA_mat() {
		return a_mat;
	}

	public void setA_mat(String a_mat) {
		this.a_mat = a_mat;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getCod_esp() {
		return cod_esp;
	}

	public void setCod_esp(int cod_esp) {
		this.cod_esp = cod_esp;
	}

	public int getTipo_ficha() {
		return tipo_ficha;
	}

	public void setTipo_ficha(int tipo_ficha) {
		this.tipo_ficha = tipo_ficha;
	}
	
	public Long getCod_centro() {
		return cod_centro;
	}

	public void setCod_centro(Long cod_centro) {
		this.cod_centro = cod_centro;
	}

	public Profesional(Long idProfesional, int fkIdUsuario, String nombre, String a_pat, String a_mat, String direccion,
			String telefono, int cod_esp, int tipo_ficha, Long cod_centro, String email) {
		super();
		this.idProfesional = idProfesional;
		this.fkIdUsuario = fkIdUsuario;
		this.nombre = nombre;
		this.a_pat = a_pat;
		this.a_mat = a_mat;
		this.direccion = direccion;
		this.telefono = telefono;
		this.cod_esp = cod_esp;
		this.tipo_ficha = tipo_ficha;
		this.cod_centro = cod_centro;
		this.email = email;
	}

	public Profesional() {
		
	}

	@Override
	public String toString() {
		return "Profesional [idProfesional=" + idProfesional + ", fkIdUsuario=" + fkIdUsuario + ", nombre=" + nombre + ", a_pat="
				+ a_pat + ", a_mat=" + a_mat + ", direccion=" + direccion + ", telefono=" + telefono + ", cod_esp="
				+ cod_esp + ", tipo_ficha=" + tipo_ficha + ", cod_centro=" + cod_centro + ", email=" + email + "]";
	}	
	
}
