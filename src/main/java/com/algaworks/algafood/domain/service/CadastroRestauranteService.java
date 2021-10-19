package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.*;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	public static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removido pois está em uso";

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		try {
			Cozinha cozinha = cozinhaService.buscarOuFalhar(restaurante.getCozinha().getId());
			restaurante.setCozinha(cozinha);
		}catch (CozinhaNaoEncontradaException e){
			throw new NegocioException(e.getMessage(), e);
		}
			return restauranteRepository.save(restaurante);
	}

	@Transactional
	public void ativar(Long restauranteId){
		Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

		restauranteAtual.ativar();
	}

	@Transactional
	public void inativar(Long restauranteId){
		Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

		restauranteAtual.inativar();
	}

	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(
				()-> new RestauranteNaoEncontradoException(restauranteId));
	}

	public List<Restaurante> listar(){
		return restauranteRepository.findAll();
	}

	@Transactional
	public Restaurante alterar(Long restauranteId, Restaurante restaurante) {
		Restaurante restauranteAtul = buscarOuFalhar(restauranteId);
		BeanUtils.copyProperties(restaurante, restauranteAtul, "id", "formasPagamento", "endereco", "dataCadastro");
		return salvar(restauranteAtul);
	}

	@Transactional
	public void excluir(Long restauranteId) {
		try{
			restauranteRepository.deleteById(restauranteId);

		}catch (EmptyResultDataAccessException e){
			throw new RestauranteNaoEncontradoException(restauranteId);

		}catch (DataIntegrityViolationException e){
			throw new EntidadeEmUsoException(
					String.format(MSG_RESTAURANTE_EM_USO, restauranteId));
		}
	}
}
