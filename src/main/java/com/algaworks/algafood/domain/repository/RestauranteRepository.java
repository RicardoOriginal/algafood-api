package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;


/**
 * Interfase responsável por percistir dados de restaurantes
 *
 * @author ricardolima.ti@gmail.com
 */
@Repository
public interface RestauranteRepository {
	
	/**
	 * Metodo responsável por buscar todos os restaurantes
	 * @return lista de cozinhas
	 */
	List<Restaurante> todos();
	
	/**
	 * Método responsável por buscar um restaurante pelo codigo de identificação
	 * @param id
	 * @return cozinha
	 */
	Restaurante porId(Long id);
	
	/**
	 * Metódo responsável por adicionar ou alterar um restaurante
	 * @param cozinha
	 * @return cozinha
	 */
	Restaurante salvar(Restaurante restaurante);
	
	/**
	 * Metódo responsável por excluir um restaurante
	 * @param cozinha
	 */
	void remover(Restaurante restaurante);
}
