package com.javafee.hibernate.dto.association.scan;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "part")
@SequenceGenerator(name = "seq_part", sequenceName = "seq_part", allocationSize = 1)
public class Part implements Cloneable, IScan {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_part")
	@Column(name = "id_part", unique = false, nullable = false, insertable = true, updatable = true)
	private Integer idPartType;

	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	private String name;

	@Column(name = "image", unique = false, nullable = true, insertable = true, updatable = true)
	private byte[] image;

	@ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_part_type", unique = false, nullable = true, insertable = true, updatable = true)
	private CarPartType carPartType;

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

}
