package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Permissao;

/**
 * Repositório responsável por percistir dados das permissões
 *
 * @author ricardolima.ti@gmail.com
 */
@Repository
public interface PermissaoRepository {

	/**
	 * Método responsável por salvar uma permissão
	 * @return Permissao
	 */
	Permissao salvar(Permissao permissao);
	
	/**
	 * Método responsável por buscar uma permissão pelo código
	 * @param id
	 * @return Permissao
	 */
	Permissao porId(Long id);
	
	/**
	 * Método responsável por buscar todas as permissões
	 * @return
	 */
	List<Permissao> todas();
	
	/**
	 * Método responsável por remover uma permissão
	 * @param id
	 */
	void remover(Permissao permissao);
}
