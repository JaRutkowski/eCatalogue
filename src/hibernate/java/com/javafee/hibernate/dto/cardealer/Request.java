package com.javafee.hibernate.dto.cardealer;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.javafee.hibernate.dto.association.scan.CarType;

import lombok.Data;

@Data
@Entity
@Table(name = "request")
@SequenceGenerator(name = "seq_request", sequenceName = "seq_request", allocationSize = 1)
public class Request implements Cloneable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_request")
	@Column(name = "id_request", unique = false, nullable = false, insertable = true, updatable = true)
	private Integer idRequest;

	@Column(name = "email", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	private String email;

	@Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true)
	private String content;

	@OneToOne
	@JoinColumn(name = "id_car")
	private CarType car;

	@Temporal(TemporalType.DATE)
	@Column(name = "date", unique = false, nullable = true, insertable = true, updatable = true, length = 13)
	private Date date;

	@Column(name = "closed", unique = false, nullable = true, insertable = true, updatable = true)
	private Boolean closed = false;

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