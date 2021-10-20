package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Modelo responsável por representar as permissões de usuários
 *
 * @author ricardolima.ti@gmail.com
 */
@Getter
@Setter
public class PermissaoModel {
	private Long id;
	private String nome;
	private String descricao;
}
