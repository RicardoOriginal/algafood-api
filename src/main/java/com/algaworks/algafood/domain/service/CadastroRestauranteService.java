package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
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
	private CadastroCozinhaService cozinhaService;
	
	public List<Restaurante> todos(){
		List<Restaurante> restaurantes = restauranteRepository.findAll();
		
		if(restaurantes.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Não há cadastro de restaurantes.");
		}
		
		return restaurantes;
	}
	
	public Restaurante buscarPor(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(
				()-> new EntidadeNaoEncontradaException(
						String.format("Não há restaurante no cadastro com código: %d", restauranteId)));
	}

	public Restaurante salvar(Restaurante restaurante) {
		Cozinha cozinha = cozinhaService.buscarPor(restaurante.getCozinha().getId());
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}
	
	public Restaurante alterar(Restaurante restaurante) {
		Restaurante restauranteAtul = buscarPor(restaurante.getId());
		BeanUtils.copyProperties(restaurante, restauranteAtul, "id");
		return salvar(restauranteAtul);
	}
	
	public void excluir(Long restauranteId) {
		buscarPor(restauranteId);
		restauranteRepository.deleteById(restauranteId);
	}
}
