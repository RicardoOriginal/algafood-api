package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cidade;

/**
 * Classe responsável por persistir dados de cidade
 *
 * @author ricardolima.ti@gmail.com
 */
@Repository
public interface CidadeRepository {

	/**
	 * Incluir e alterar uma cidade
	 * @param cidade
	 * @return cidade
	 */
	Cidade salvar(Cidade cidade);
	
	/**
	 * Buscar cidade pelo Id
	 * @param id
	 * @return cidade
	 */
	Cidade porId(Long id);
	
	/**
	 * Busca todas as cidades
	 * @return lista de cidades
	 */
	List<Cidade> todas();
	
	/**
	 * remove uma cidade pelo Id
	 * @param id
	 */
	void remover(Long id);
}
