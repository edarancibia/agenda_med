package com.calendar.entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.engine.spi.CascadeStyle;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="events")
public class Events2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="start")
	@JsonProperty("start")
	//@JsonFormat(pattern="dd/MM/yyyy")
	private String start;
	
	@Column(name="end")
	//@JsonFormat(pattern="dd/MM/yyyy")
	private String end;
	
	@Column(name="description")
	private String description;
	
	@Column(name="rut_num")
	private int rut_num;
	
	/*@ManyToOne(optional=true, fetch=FetchType.LAZY)
	@JoinColumn(name="dni")
	private Paciente paciente;*/
	
	public int getRut_num() {
		return rut_num;
	}

	public void setRut_num(int rut_num) {
		this.rut_num = rut_num;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public Events2(long id, String start, String end, String description, int rut_num) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.description = description;
		this.rut_num = rut_num;
		
	}

	public Events2() {
		
	}

	@Override
	public String toString() {
		return "Events2 [id=" + id + ", start=" + start + ", end=" + end + ", description=" + description + ", rut_num="
				+ rut_num + "]";
	}
	
	
}
