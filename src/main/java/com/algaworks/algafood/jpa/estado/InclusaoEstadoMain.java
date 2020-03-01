package com.algaworks.algafood.jpa.estado;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

/**
 * Classe responsável por incluir estado na base
 *
 * @author ricardolima.ti@gmail.com
 */
public class InclusaoEstadoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepository estados = applicationContext.getBean(EstadoRepository.class);
		
		Estado saoPaulo = new Estado();
		saoPaulo.setNome("São Paulo");
		
		Estado rioDeJaneiro = new Estado();
		rioDeJaneiro.setNome("Rio de Janeiro");
		
		estados.salvar(saoPaulo);
		estados.salvar(rioDeJaneiro);
	}
	
}
