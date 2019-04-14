package com.javafee.hibernate.dto.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@NamedQueries(@NamedQuery(name = "SystemProperties.checkIfExistsForUser", query = "from SystemProperties where id_user_data = :idUserData"))
@Table(name = "system_properties")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "seq_system_properties", sequenceName = "seq_system_properties", allocationSize = 1)
public class SystemProperties {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_system_properties")
	@Column(name = "id_system_properties", unique = false, nullable = false, insertable = true, updatable = true)
	private Integer idSystemData;

	@Column(name = "language", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	private String language;

	@Column(name = "color", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	private String color;

	@Column(name = "font_size", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	private Integer fontSize;

	@OneToOne
	@JoinColumn(name = "id_user_data")
	private UserData userData;
}
