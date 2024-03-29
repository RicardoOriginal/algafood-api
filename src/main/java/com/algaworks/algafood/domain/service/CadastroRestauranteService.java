package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.*;
import com.algaworks.algafood.domain.model.*;
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

	@Autowired
	private CadastroCidadeService cidadeService;

	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	public static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removido pois está em uso";

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		final Long cozinhaId = restaurante.getCozinha().getId();
		final Long cidadeId = restaurante.getEndereco().getCidade().getId();

		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);

		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);

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

	@Transactional
	public void ativar(List<Long> restauranteIds){
		try {
			restauranteIds.forEach(this::ativar);
		}catch (RestauranteNaoEncontradoException e){
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Transactional
	public void inativar(List<Long> restauranteIds){
		try {
			restauranteIds.forEach(this::inativar);
		}catch (RestauranteNaoEncontradoException e){
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Transactional
	public void resassociarFormaPagamento(Long restauranteId, Long formaPagamentoId){
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
		restaurante.removerFormaPagamento(formaPagamento);
	}

	@Transactional
	public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId){
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
		restaurante.adicionarFormaPagamento(formaPagamento);
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

	@Transactional
	public void abrir(Long restauranteId){
		final Restaurante restaurante = buscarOuFalhar(restauranteId);
		restaurante.abrir();
	}

	@Transactional
	public void fechar(Long restauranteId){
		final Restaurante restaurante = buscarOuFalhar(restauranteId);
		restaurante.fechar();
	}

	@Transactional
	public void associarResponsavel(Long restauranteId, Long usuarioId){
		final Restaurante restaurante = buscarOuFalhar(restauranteId);
		final Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		restaurante.removerResponsavel(usuario);
	}

	@Transactional
	public void desassociarResponsavel(Long restauranteId, Long usuarioId){
		final Restaurante restaurante = buscarOuFalhar(restauranteId);
		final Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		restaurante.adicionarResponsavel(usuario);
	}
}
