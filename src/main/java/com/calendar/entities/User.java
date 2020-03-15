package com.calendar.entities;

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
	private long idusuario;
	
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

	public long getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(long idusuario) {
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

	public User(long idusuario, String apat, String nombre, String amat, String email, String pass, int perfil) {
		this.idusuario = idusuario;
		this.apat = apat;
		this.nombre = nombre;
		this.amat = amat;
		this.email = email;
		this.pass = pass;
		this.perfil = perfil;
	}

	public User() {
		
	}

	@Override
	public String toString() {
		return "User [idusuario=" + idusuario + ", apat=" + apat + ", nombre=" + nombre + ", amat=" + amat + ", email="
				+ email + ", pass=" + pass + ", perfil=" + perfil + "]";
	}
	
	
}
