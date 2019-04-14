package com.javafee.hibernate.dto.association.scan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "car_type")
@SequenceGenerator(name = "seq_car_type", sequenceName = "seq_car_type", allocationSize = 1)
public class CarType implements Cloneable, IScan {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_car_type")
	@Column(name = "id_car_type", unique = false, nullable = false, insertable = true, updatable = true)
	private Integer idCarType;

	@Column(name = "manufacturer", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	private String manufacturer;

	@Column(name = "model", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	private String model;

	@Column(name = "fuel_kind", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	private String fuelKind;

	@Column(name = "power", unique = false, nullable = true, insertable = true, updatable = true)
	private Integer power;

	@Column(name = "engine", unique = false, nullable = true, insertable = true, updatable = true)
	private Double engine;

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

	public String toString() {
		return this.manufacturer + " " + this.model + " " + engine;
	}

}