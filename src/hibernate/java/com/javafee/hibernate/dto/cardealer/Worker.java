package com.javafee.hibernate.dto.cardealer;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.javafee.hibernate.dto.common.UserData;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@NamedQueries({@NamedQuery(name = "Worker.checkIfWorkerLoginExist", query = "from Worker where login = :login"),
		@NamedQuery(name = "Worker.checkWithComparingIdIfClientLoginExist", query = "from Worker where login = :login and id != :id")})
@Table(name = "worker")
@PrimaryKeyJoinColumn(name = "id_worker", referencedColumnName = "id_user_data")
public class Worker extends UserData implements Cloneable {
	@Column(name = "salary", unique = false, nullable = true, insertable = true, updatable = true, precision = 9, scale = 2)
	private BigDecimal salary;

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
		String result = "";
		if (getSurname() != null && getName() == null)
			result = getSurname();
		else if (getSurname() == null && getName() != null)
			result = getName();
		else if (getSurname() != null && getName() != null)
			result = getSurname() + " " + getName();
		return result;
	}
}
