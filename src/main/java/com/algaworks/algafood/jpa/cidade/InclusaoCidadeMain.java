/**
 * 
 */
package com.algaworks.algafood.jpa.cidade;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;

/**
 * Classe reponsável por incluir cidade
 *
 * @author ricardolima.ti@gmail.com
 */
public class InclusaoCidadeMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepository cidades = applicationContext.getBean(CidadeRepository.class);
		
		Cidade cidade = new Cidade();
		cidade.setNome("Jatai");
		
		Estado estado = new Estado();
		estado.setId(1L);
		
		cidade.setEstado(estado);
		
		cidades.save(cidade);
	}

}
