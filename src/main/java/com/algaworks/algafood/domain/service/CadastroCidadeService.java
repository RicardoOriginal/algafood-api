package com.algaworks.algafood.domain.service;

import java.util.List;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;

/**
 * Classe responsável por implementar serviços sobre cidades
 *
 * @author ricardolima.ti@gmail.com
 */

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroEstadoService estadoService;

	public static final String MSG_CIDADE_NAO_ENCONTRADA = "Não há cidade cadastrada com o código: %d";
	public static final String MSG_CIDADE_EM_USO = "Cozinha de código %d não pode ser removida pois está em uso";
	
	public List<Cidade> listar(){
		return cidadeRepository.findAll();
	}
	
	public Cidade buscarPor(Long cidadeId) {
		return cidadeRepository.findById(cidadeId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(
						String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId)));
	}
	
	public Cidade adicionar(Cidade cidade) {
		Estado estado = estadoService.buscarPor(cidade.getEstado().getId());
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}
	
	public Cidade alterar(Long cidadeId, Cidade cidade) {
		Cidade cidadeAtual = buscarPor(cidadeId);
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		return adicionar(cidadeAtual);
	}

	public void remover(Long cidadeId) {
		buscarPor(cidadeId);
		try{
			cidadeRepository.deleteById(cidadeId);
		}catch (EmptyResultDataAccessException e ){
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_CIDADE_NAO_ENCONTRADA,cidadeId));

		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}
}
