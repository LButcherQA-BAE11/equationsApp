package com.bae.equationSaverApp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String equationName;
	@Column
	private String equation;
	@Column
	private String description;
	@Column
	private String subject;

	public Equations(long id, String equationName, String equation, String description, String subject) {
		super();
		this.id = id;
		this.equationName = equationName;
		this.equation = equation;
		this.description = description;
		this.subject = subject;
	}

	public Equations(String equationName, String equation, String description, String subject) {

		this.equationName = equationName;
		this.equation = equation;
		this.description = description;
		this.subject = subject;
	}

	Equations() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEquationName() {
		return equationName;
	}

	public void setEquationName(String equationName) {
		this.equationName = equationName;
	}

	public String getEquation() {
		return equation;
	}

	public void setEquation(String equation) {
		this.equation = equation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
