package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe responsável por implementar repositório de formas de pagamento
 *
 * @author ricardolima.ti@gmail.com
 */
@Repository
public abstract class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

//	@PersistenceContext
//	private EntityManager manager;
//
//	@Override
//	public List<FormaPagamento> todas(){
//		return manager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
//	}
//
//	@Transactional
//	@Override
//	public FormaPagamento salvar(FormaPagamento formaPagamento) {
//		return manager.merge(formaPagamento);
//	}
//
//	@Override
//	public FormaPagamento porId(Long id) {
//		return manager.find(FormaPagamento.class, id);
//	}
//
//	@Transactional
//	@Override
//	public void remover(FormaPagamento formaPagamento) {
//		formaPagamento = porId(formaPagamento.getId());
//
//		if(formaPagamento == null) {
//			throw new EmptyResultDataAccessException(1);
//		}
//
//		manager.remove(formaPagamento);
//	}

}
