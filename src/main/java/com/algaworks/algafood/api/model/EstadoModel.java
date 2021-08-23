package com.algaworks.algafood.api.model;

import com.algaworks.algafood.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * Modelo responsável por transportar dados de estado
 *
 * @author ricardolima.ti@gmail.com
 */
@Getter
@Setter
public class EstadoModel {
	private Long id;
	private String nome;
}
