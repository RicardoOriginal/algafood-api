package com.algaworks.algafood.jpa.restaurante;

import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

/**
 * Serviço responsável por iniciar aplicação fazer a consulta e fechar a aplicação
 *
 * @author ricardolima.ti@gmail.com
 */
public class BuscaRestauranteMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository repository = applicationContext.getBean(RestauranteRepository.class);
		
		Long restauranteId = 1L;
		
		Restaurante restaurante = repository.findById(restauranteId).orElseThrow(
				() -> new RestauranteNaoEncontradoException(restauranteId));
		
		System.out.println(restaurante.getNome());
	}
}
