package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.FormaPagamento;


/**
 * Interfase responsável por transacionar dados de formas de pagamento
 *
 * @author ricardolima.ti@gmail.com
 */
@Repository
public interface FormaPagamentoRepository {
	
	/**
	 * Metodo responsável por buscar todas as formas de pagamento
	 * @return lista de formas de pagamentos
	 */
	List<FormaPagamento> todas();
	
	/**
	 * Método responsável por buscar uma forma de pagamento pelo codigo de identificação
	 * @param id
	 * @return cozinha
	 */
	FormaPagamento porId(Long id);
	
	/**
	 * Metódo responsável por adicionar ou alterar uma forma de pagamento
	 * @param cozinha
	 * @return cozinha
	 */
	FormaPagamento salvar(FormaPagamento formaPagamento);
	
	/**
	 * Metódo responsável por excluir uma forma de pagamento
	 * @param cozinha
	 */
	void remover(FormaPagamento formaPagamento);
}
