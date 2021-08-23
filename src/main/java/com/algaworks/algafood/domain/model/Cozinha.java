package com.algaworks.algafood.domain.model;

import com.algaworks.algafood.Groups;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
	@NotNull(groups = Groups.CozinhaId.class)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
//	@JsonIgnore
//	@JsonProperty("titulo")
	@NotBlank
	@Column(nullable = false)
	private String nome;

//	@Column(name = "observacao", nullable = false)
//	private Integer descricao;
	
	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante> restaurantes = new ArrayList<>();
}