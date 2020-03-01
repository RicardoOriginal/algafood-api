package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Estado;

/**
 * Repositório responsável por persistir dados de estado
 *
 * @author ricardolima.ti@gmail.com
 */
@Repository
public interface EstadoRepository {
	
	/**
	 * Inserir e alterar estado
	 * @param estado
	 * @return estado
	 */
	Estado salvar(Estado estado);
	
	/**
	 * Busca estado pelo código
	 * @param id
	 * @return estado
	 */
	Estado porId(Long id);
	
	/**
	 * Busca todos os estados
	 * @return lista de estados
	 */
	List<Estado> todos();
	
	/**
	 * Remove estado
	 * @param estado
	 */
	void remove(Long id);

}
