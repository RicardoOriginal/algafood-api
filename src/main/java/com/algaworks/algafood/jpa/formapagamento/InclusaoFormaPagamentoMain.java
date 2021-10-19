package com.algaworks.algafood.jpa.formapagamento;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

/**
 * Serviço responsável por iniciar aplicação fazer a consulta e fechar a aplicação
 *
 * @author ricardolima.ti@gmail.com
 */
public class InclusaoFormaPagamentoMain {
	
	public static void main(String[] args) {
//		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
//				.web(WebApplicationType.NONE)
//				.run(args);
//
//		FormaPagamentoRepository formasPagamento = applicationContext.getBean(FormaPagamentoRepository.class);
//
//		FormaPagamento dinheiro = new FormaPagamento();
//		dinheiro.setDescricao("Dinheiro");
//
//		FormaPagamento cartaoDebito = new FormaPagamento();
//		cartaoDebito.setDescricao("Cartao de débito");
//
//		FormaPagamento cartaoCredito = new FormaPagamento();
//		cartaoCredito.setDescricao("Cartao de crédito");
//
//		dinheiro = formasPagamento.salvar(dinheiro);
//		cartaoDebito = formasPagamento.salvar(cartaoDebito);
//		cartaoCredito = formasPagamento.salvar(cartaoCredito);
//
//		System.out.printf("%d - %s\n", dinheiro.getId(), dinheiro.getDescricao());
//		System.out.printf("%d - %s\n", cartaoDebito.getId(), cartaoDebito.getDescricao());
//		System.out.printf("%d - %s\n", cartaoCredito.getId(), cartaoCredito.getDescricao());
	}
}
