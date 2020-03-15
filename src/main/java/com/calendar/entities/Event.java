package com.calendar.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="events")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="rut_num")
	private Long rut_num;
	
	@Column(name="fini")
	private String fini;
	
	@Column(name="ffin")
	private String ffin;
	
	@Column(name="rut_med")
	private Long rut_med;
	
	@Column(name="obs")
	private String obs;
	
	@Column(name="estado")
	private int estado;
	
	@Column(name="fecha")
	private String fecha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRut_num() {
		return rut_num;
	}

	public void setRut_num(Long rut_num) {
		this.rut_num = rut_num;
	}

	public String getFini() {
		return fini;
	}

	public void setFini(String fini) {
		this.fini = fini;
	}

	public String getFfin() {
		return ffin;
	}

	public void setFfin(String ffin) {
		this.ffin = ffin;
	}

	public Long getRut_med() {
		return rut_med;
	}

	public void setRut_med(Long rut_med) {
		this.rut_med = rut_med;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Event(Long id, Long rut_num, String fini, String ffin, Long rut_med, String obs, int estado,
			String fecha) {
		
		this.id = id;
		this.rut_num = rut_num;
		this.fini = fini;
		this.ffin = ffin;
		this.rut_med = rut_med;
		this.obs = obs;
		this.estado = estado;
		this.fecha = fecha;
	}

	public Event() {
		
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", rut_num=" + rut_num + ", fini=" + fini + ", ffin=" + ffin + ", rut_med=" + rut_med
				+ ", obs=" + obs + ", estado=" + estado + ", fecha=" + fecha + "]";
	}
	
	
}
