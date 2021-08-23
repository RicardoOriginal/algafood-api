package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;


/**
 * Modelo responsável por transportar dados de estado
 *
 * @author ricardolima.ti@gmail.com
 */
public class EstadoInput {

	@NotBlank
	private String nome;
}
