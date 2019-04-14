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
@Table(name = "sale")
@SequenceGenerator(name = "seq_sale", sequenceName = "seq_sale", allocationSize = 1)
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sale")
	@Column(name = "id_sale", unique = false, nullable = false, insertable = true, updatable = true)
	private Integer idSale;

	@OneToOne
	@JoinColumn(name = "id_client")
	private Client client;

	@OneToOne
	@JoinColumn(name = "id_seller")
	private Worker saller;

	@OneToOne
	@JoinColumn(name = "id_car")
	private CarType car;

	@Temporal(TemporalType.DATE)
	@Column(name = "date", unique = false, nullable = true, insertable = true, updatable = true, length = 13)
	private Date date;

	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 400)
	private String remark;

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
