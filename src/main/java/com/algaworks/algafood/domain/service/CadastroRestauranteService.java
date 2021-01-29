package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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

	public static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Não há restaurante no cadastro com o código: %d";
	public static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removido pois está em uso";
	
	public List<Restaurante> listar(){
		return restauranteRepository.findAll();
	}
	
	public Restaurante buscarPor(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(
				()-> new EntidadeNaoEncontradaException(
						String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId)));
	}

	public Restaurante salvar(Restaurante restaurante) {
		Cozinha cozinha = cozinhaService.buscarOuFalhar(restaurante.getCozinha().getId());
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}
	
	public Restaurante alterar(Long restauranteId, Restaurante restaurante) {
		Restaurante restauranteAtul = buscarPor(restauranteId);
		BeanUtils.copyProperties(restaurante, restauranteAtul, "id", "formasPagamento", "endereco", "dataCadastro");
		return salvar(restauranteAtul);
	}
	
	public void excluir(Long restauranteId) {
		buscarPor(restauranteId);
		try{
			restauranteRepository.deleteById(restauranteId);

		}catch (EmptyResultDataAccessException e){
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId));

		}catch (DataIntegrityViolationException e){
			throw new EntidadeEmUsoException(
					String.format(MSG_RESTAURANTE_EM_USO, restauranteId));
		}
	}
}
