package com.algaworks.algafood.jpa.restaurante;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

/**
 * Serviço responsável por iniciar aplicação fazer a consulta e fechar a aplicação
 *
 * @author ricardolima.ti@gmail.com
 */
public class AlteracaoRestauranteMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository repository = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("Bom de Prato");
		restaurante1.setTaxaFrete(new BigDecimal(6.6));
		
		Restaurante restaurante2 = new Restaurante();
		restaurante2.setNome("Casa da Vovó");
		restaurante2.setTaxaFrete(new BigDecimal(8.8));
		
		restaurante1 = repository.save(restaurante1);
		restaurante2 = repository.save(restaurante2);
		
		System.out.printf("%d - %s\n", restaurante1.getId(), restaurante1.getNome());
		System.out.printf("%d - %s\n", restaurante2.getId(), restaurante2.getNome());
	}
}
