package com.javafee.hibernate.dto.association.scan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@NamedQueries(@NamedQuery(name = "CarPartType.checkIfCarPartsExists", query = "from CarPartType where carType.idCarType = :idCarType"))
@Table(name = "part_type")
@SequenceGenerator(name = "seq_part_type", sequenceName = "seq_part_type", allocationSize = 1)
public class CarPartType implements Cloneable, IScan {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_part_type")
	@Column(name = "id_part_type", unique = false, nullable = false, insertable = true, updatable = true)
	private Integer idPartType;

	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	private String name;

	@Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	private String description;

	@Column(name = "origin", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	private String origin;

	@Column(name = "producer", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	private String producer;

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_car_type", unique = false, nullable = true, insertable = true, updatable = true)
	private CarType carType;

	@Override
	public Object clone() {
		Object result = null;
		try {
			result = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
