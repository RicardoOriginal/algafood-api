package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

/**
 * Classe responável pela implementação do repositório de estado
 *
 * @author ricardolima.ti@gmail.com
 */
@Repository
public class EstadoRepositoryImpl implements EstadoRepository{

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@Override
	public Estado salvar(Estado estado) {
		return manager.merge(estado);
	}

	@Override
	public Estado porId(Long id) {
		return manager.find(Estado.class, id);
	}

	@Override
	public List<Estado> todos() {
		return manager.createQuery("from Estado", Estado.class).getResultList();
	}

	@Transactional
	@Override
	public void remove(Long id) {
		Estado estado = porId(id);
		
		if(estado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(estado);
	}
}
