package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;


/**
 * Interfase responsável por transacionar cozinhas
 *
 * @author ricardolima.ti@gmail.com
 */
@Repository
public interface CozinhaRepository {
	
	/**
	 * Metodo responsável por buscar todas as cozinhas
	 * @return lista de cozinhas
	 */
	List<Cozinha> todas();
	
	/**
	 * Método responsável por buscar uma cozinha pelo codigo de identificação
	 * @param id
	 * @return cozinha
	 */
	Cozinha porId(Long id);
	
	/**
	 * Metódo responsável por adicionar ou alterar uma cozinha
	 * @param cozinha
	 * @return cozinha
	 */
	Cozinha salvar(Cozinha cozinha);
	
	/**
	 * Metódo responsável por excluir uma cozinha
	 * @param cozinha
	 */
	void remover(Cozinha cozinha);
}
