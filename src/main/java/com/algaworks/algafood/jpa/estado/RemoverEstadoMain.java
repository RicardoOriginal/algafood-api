package com.algaworks.algafood.jpa.estado;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.repository.EstadoRepository;

/**
 * Classe responsável por remover um estado
 *
 * @author ricardolima.ti@gmail.com
 */
public class RemoverEstadoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepository estados = applicationContext.getBean(EstadoRepository.class);
		
		estados.deleteById(1L);
	}
}
