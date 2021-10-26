package com.algaworks.algafood.jpa.permissao;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

/**
 * Serviço responsável por iniciar aplicação fazer a consulta e fechar a aplicação
 *
 * @author ricardolima.ti@gmail.com
 */
public class AlteracaoPermissaoMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepository permissoes = applicationContext.getBean(PermissaoRepository.class);
		
		Permissao permissao = new Permissao();
		permissao.setId(1L);
		permissao.setNome("Alterar usuários");
		permissao.setDescricao("Permite alterar usuários");
		
//		permissoes.salvar(permissao);
	}
}
