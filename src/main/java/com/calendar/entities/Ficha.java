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
	private Double peso;
	
	@Column(name = "estatura")
	private Double estatura;
	
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

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getEstatura() {
		return estatura;
	}

	public void setEstatura(Double estatura) {
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

	public Ficha(Long idFicha, Date fecha, int rutPac, Double peso, Double estatura, String motivo, String antecedentes,
			String indicaciones, String examenFisico, int idProfesional, String obs, String diagnostico) {
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
	}

	public Ficha() {
		
	}

}
