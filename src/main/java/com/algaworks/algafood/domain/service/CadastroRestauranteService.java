package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

/**
 * Serviço responsável por implementar regras de negócio de restaurante
 *
 * @author ricardolima.ti@gmail.com
 */

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public List<Restaurante> todos(){
		return restauranteRepository.todos();
	}
	
	public Restaurante buscar(Long restauranteId) {
		return restauranteRepository.porId(restauranteId);
	}

	public Restaurante salvar(Restaurante restaurante) {
		
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de cozinha com o código %d", cozinhaId)));
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.salvar(restaurante);
	}
	
	public Restaurante alterar(Restaurante restaurante) {
		
		Long codigoCozinha = restaurante.getCozinha().getId();
		
		cozinhaRepository.findById(codigoCozinha)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("Não existe cadastro de cozinha com o código %d", codigoCozinha)));
		
		return restauranteRepository.salvar(restaurante);
	}
}
