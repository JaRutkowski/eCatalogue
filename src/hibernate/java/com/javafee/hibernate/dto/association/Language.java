package com.javafee.hibernate.dto.association;

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
@Table(name = "language")
@SequenceGenerator(name = "seq_language", sequenceName = "seq_language", allocationSize = 1)
public class Language {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_language")
	@Column(name = "id_language", unique = false, nullable = false, insertable = true, updatable = true)
	private Integer idLanguage;

	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	private String name;
}
