package com.algaworks.algafood.jpa.formapagamento;

import java.util.List;

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
public class ConsultaFormaPagamentoMain {
	
	public static void main(String[] args) {
//		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
//				.web(WebApplicationType.NONE)
//				.run(args);
//
//		FormaPagamentoRepository formasPagamento = applicationContext.getBean(FormaPagamentoRepository.class);
//
//		List<FormaPagamento> todasFormasPagamento = formasPagamento.todas();
//
//		todasFormasPagamento.forEach(formaPagamento ->{
//			System.out.printf("%d - %s\n", formaPagamento.getId(), formaPagamento.getDescricao());
//		});
	}
}
