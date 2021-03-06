package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

/**
 * Classe responsável por implementar o respositorio de permissão
 *
 * @author ricardolima.ti@gmail.com
 */
@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository{
	
	@PersistenceContext
	private EntityManager maneger;

	@Override
	@Transactional
	public Permissao salvar(Permissao permissao) {
		return maneger.merge(permissao);
	}

	@Override
	public Permissao porId(Long id) {
		return maneger.find(Permissao.class, id);
	}

	@Override
	public List<Permissao> todas() {
		return maneger.createQuery("from Permissao", Permissao.class).getResultList();
	}

	@Override
	@Transactional
	public void remover(Permissao permissao) {
		permissao = porId(permissao.getId());
		
		if(permissao == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		maneger.remove(permissao);
	}

	
}
