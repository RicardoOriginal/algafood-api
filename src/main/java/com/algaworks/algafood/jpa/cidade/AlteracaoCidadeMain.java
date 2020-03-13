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
 * Classe responsável por alterar uma cidade
 *
 * @author ricardolima.ti@gmail.com
 */
public class AlteracaoCidadeMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepository cidades = applicationContext.getBean(CidadeRepository.class);
		
		Cidade jatai = new Cidade();
		jatai.setId(1L);
		jatai.setNome("Jatai");
		
		Estado goias = new Estado();
		goias.setId(1L);
		
		jatai.setEstado(goias);
		
		cidades.save(jatai);
	}

}
