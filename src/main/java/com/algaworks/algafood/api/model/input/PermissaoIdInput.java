package com.algaworks.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Modelo responsável por representar as permissões de usuários
 *
 * @author ricardolima.ti@gmail.com
 */
@Getter
@Setter
public class PermissaoIdInput {
	@NotNull
	private Long id;
}
