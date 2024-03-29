package com.calendar.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long idusuario;
	
	@Column(name="a_pat")
	private String apat;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="a_mat")
	private String amat;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String pass;
	
	@Column(name="perfil")
	private int perfil;
	
	@Column(name = "vigente")
	private int vigente;
	
	@Column(name = "created_at")
	private Date created_at;
	
	@Column(name = "updated_at")
	private Date updated_at;

	public Long getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}

	public String getApat() {
		return apat;
	}

	public void setApat(String apat) {
		this.apat = apat;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAmat() {
		return amat;
	}

	public void setAmat(String amat) {
		this.amat = amat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getPerfil() {
		return perfil;
	}

	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}

	
	public int getVigente() {
		return vigente;
	}

	public void setVigente(int vigente) {
		this.vigente = vigente;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}


	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	
	public User(Long idusuario, String apat, String nombre, String amat, String email, String pass, int perfil,
			int vigente, Date created_at, Date updated_at) {
		this.idusuario = idusuario;
		this.apat = apat;
		this.nombre = nombre;
		this.amat = amat;
		this.email = email;
		this.pass = pass;
		this.perfil = perfil;
		this.vigente = vigente;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public User() {
		
	}

	@Override
	public String toString() {
		return "User [idusuario=" + idusuario + ", apat=" + apat + ", nombre=" + nombre + ", amat=" + amat + ", email="
				+ email + ", pass=" + pass + ", perfil=" + perfil + ", vigente=" + vigente + ", created_at="
				+ created_at + ", updated_at=" + updated_at + "]";
	}

	
}
