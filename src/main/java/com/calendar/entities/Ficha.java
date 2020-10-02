package com.calendar.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ficha")
public class Ficha {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idficha")
	private Long idFicha;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "rut_pac")
	private int rutPac;
	
	@Column(name = "peso")
	private String peso;
	
	@Column(name = "estatura")
	private String estatura;
	
	@Column(name = "motivo")
	private String motivo;
	
	@Column(name = "antecedentes")
	private String antecedentes;
	
	@Column(name = "indicaciones")
	private String indicaciones;
	
	@Column(name = "examen_fis")
	private String examenFisico;
	
	@Column(name = "idprofesional")
	private int idProfesional;
	
	@Column(name = "obsgenerales")
	private String obs;
	
	@Column(name = "diagnostico")
	private String diagnostico;
	
	@Column(name = "sol_exam")
	private String solExamen;
	
	@Column(name = "edad")
	private String edad;

	public Long getIdFicha() {
		return idFicha;
	}

	public void setIdFicha(Long idFicha) {
		this.idFicha = idFicha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getRutPac() {
		return rutPac;
	}

	public void setRutPac(int rutPac) {
		this.rutPac = rutPac;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getEstatura() {
		return estatura;
	}

	public void setEstatura(String estatura) {
		this.estatura = estatura;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getAntecedentes() {
		return antecedentes;
	}

	public void setAntecedentes(String antecedentes) {
		this.antecedentes = antecedentes;
	}

	public String getIndicaciones() {
		return indicaciones;
	}

	public void setIndicaciones(String indicaciones) {
		this.indicaciones = indicaciones;
	}

	public String getExamenFisico() {
		return examenFisico;
	}

	public void setExamenFisico(String examenFisico) {
		this.examenFisico = examenFisico;
	}

	public int getIdProfesional() {
		return idProfesional;
	}

	public void setIdProfesional(int idProfesional) {
		this.idProfesional = idProfesional;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	

	public String getSolExamen() {
		return solExamen;
	}

	public void setSolExamen(String solExamen) {
		this.solExamen = solExamen;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public Ficha(Long idFicha, Date fecha, int rutPac, String peso, String estatura, String motivo, String antecedentes,
			String indicaciones, String examenFisico, int idProfesional, String obs, String diagnostico,
			String solExamen, String edad) {
		this.idFicha = idFicha;
		this.fecha = fecha;
		this.rutPac = rutPac;
		this.peso = peso;
		this.estatura = estatura;
		this.motivo = motivo;
		this.antecedentes = antecedentes;
		this.indicaciones = indicaciones;
		this.examenFisico = examenFisico;
		this.idProfesional = idProfesional;
		this.obs = obs;
		this.diagnostico = diagnostico;
		this.solExamen = solExamen;
		this.edad = edad;
	}

	public Ficha() {
		
	}

}
