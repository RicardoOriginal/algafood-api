package com.algaworks.algafood.jpa.estado;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

/**
 * Classe responsável por consultar dados de um estado
 *
 * @author ricardolima.ti@gmail.com
 */
public class ConsultaEstadoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepository estados = applicationContext.getBean(EstadoRepository.class);
		
		Long estadoId = 1L;
		
		Estado estado = estados.findById(estadoId).orElseThrow(()-> new EntidadeNaoEncontradaException(
				String.format("Não há cadastro de cidade com o código: %d", estadoId)));
		
		System.out.println(estado);
	}
	
}
