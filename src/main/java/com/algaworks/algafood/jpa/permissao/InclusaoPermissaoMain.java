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
public class InclusaoPermissaoMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepository permissoes = applicationContext.getBean(PermissaoRepository.class);
		
		Permissao permissao1 = new Permissao();
		permissao1.setNome("busca usuários");
		permissao1.setDescricao("Permite buscar todos os usuários");
		
		Permissao permissao2 = new Permissao();
		permissao2.setNome("altera usuário");
		permissao2.setDescricao("Permite alterar usuário");
		
		permissao1 = permissoes.salvar(permissao1);
		permissao2 = permissoes.salvar(permissao2);
		
		System.out.printf("%d - %s\n", permissao1.getId(), permissao1.getNome());
		System.out.printf("%d - %s\n", permissao2.getId(), permissao2.getNome());
	}
}
