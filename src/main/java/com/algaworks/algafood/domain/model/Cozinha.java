package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Modelo responsável por transportar infomações da cozinha
 *
 * @author ricardolima.ti@gmail.com
 */

@JsonRootName("cozinha")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
//	@JsonIgnore
//	@JsonProperty("titulo")
	@Column(nullable = false)
	private String nome;
}