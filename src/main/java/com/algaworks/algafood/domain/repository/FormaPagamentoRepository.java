package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Interfase responsável por transacionar dados de formas de pagamento
 *
 * @author ricardolima.ti@gmail.com
 */
@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
}
