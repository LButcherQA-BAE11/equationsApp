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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((equation == null) ? 0 : equation.hashCode());
		result = prime * result + ((equationName == null) ? 0 : equationName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equations other = (Equations) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (equation == null) {
			if (other.equation != null)
				return false;
		} else if (!equation.equals(other.equation))
			return false;
		if (equationName == null) {
			if (other.equationName != null)
				return false;
		} else if (!equationName.equals(other.equationName))
			return false;
		if (id != other.id)
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

}
