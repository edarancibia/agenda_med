package com.calendar.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="paciente")
public class Paciente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="dni")
	private int dni;

	@Column(name="nombre")
	private String nombre;
	
	@Column(name="a_pat")
	private String a_pat;
	
	@Column(name="a_mat")
	private String a_mat;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="email")
	private String email;
	
	@Column(name="sexo")
	private int sexo;
	
	@Column(name="fecha_nac")
	private String fecha_nac;
	
	@OneToMany(mappedBy="rut_num", cascade=CascadeType.ALL, fetch= FetchType.LAZY)
	@JsonIgnore
	private Set<Events2> events;

	public Set<Events2> getEvents() {
		return events;
	}

	public void setEvents(Set<Events2> events) {
		this.events = events;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
	}

	public String getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(String fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	

	public Paciente(long id, int dni, String nombre, String a_pat, String a_mat, String direccion, String telefono,
			String email, int sexo, String fecha_nac, Set<Events2> events) {
		
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.a_pat = a_pat;
		this.a_mat = a_mat;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.sexo = sexo;
		this.fecha_nac = fecha_nac;
		this.events = events;
	}

	public Paciente() {
	
	}

	@Override
	public String toString() {
		return "Paciente [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", a_pat=" + a_pat + ", a_mat=" + a_mat
				+ ", direccion=" + direccion + ", telefono=" + telefono + ", email=" + email + ", sexo=" + sexo
				+ ", fecha_nac=" + fecha_nac + ", events=" + events + "]";
	}	
	
	
}
