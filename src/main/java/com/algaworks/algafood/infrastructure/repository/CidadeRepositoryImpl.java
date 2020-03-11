/**
 * 
 */
package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

/**
 * Classe responsável por implementar metodos para persistir dados de cidade
 *
 * @author ricardolima.ti@gmail.com
 */
@Repository
public class CidadeRepositoryImpl implements CidadeRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@Override
	public Cidade salvar(Cidade cidade) {
		return manager.merge(cidade);
	}

	@Override
	public Cidade porId(Long id) {
		return manager.find(Cidade.class, id);
	}

	@Override
	public List<Cidade> todas() {
		return manager.createQuery("from Cidade", Cidade.class).getResultList();
	}

	@Transactional
	@Override
	public void remover(Long id) {
		Cidade cidade = porId(id);
		
		if(cidade == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(cidade);
	}
}
