package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

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
	private EstadoRepository estadoRepository;
	
	public List<Cidade> listar(){
		List<Cidade> cidades = cidadeRepository.todas();
		
		if(cidades.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Não há cidade cadastrada.");
		}
		
		return cidades;
	}
	
	public Cidade buscar(Long cidadeId) {
		Cidade cidade = cidadeRepository.porId(cidadeId);
		
		verificaExistenciaCidade(cidadeId, cidade);
		
		return cidade;
	}

	private void verificaExistenciaCidade(Long cidadeId, Cidade cidade) {
		if(cidade == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não há cidade cadastrada com o código: %d.", cidadeId));
		}
	}
	
	public Cidade adicionar(Cidade cidade) {
		return cidadeRepository.salvar(cidade);
	}
	
	public Cidade alterar(Long cidadeId, Cidade cidade) {
		
		Cidade cidadeAtual = cidadeRepository.porId(cidadeId);
		
		verificaExistenciaCidade(cidadeId, cidadeAtual);
		
		Long estadoId = cidade.getEstado().getId();
		
		Estado estado = estadoRepository.porId(estadoId);
		
		if(estado == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não há estado cadastrado com o código: %d", estadoId));
		}
		
		cidade.setId(cidadeId);
		
		return cidadeRepository.salvar(cidade);
	}
	
	public void remover(Long cidadeId) {
		try {
			
			cidadeRepository.remover(cidadeId);
			
		}catch (EmptyResultDataAccessException e) {
			
			throw new EntidadeNaoEncontradaException(
					String.format("Não há cidade cadastrada com o código: %d.", cidadeId));
		}
	}
}
