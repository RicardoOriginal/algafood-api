package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
	
	private final String CIDADE_NAO_ENCONTRADA = "Não há cidade cadastrada com o código: %d";
	
	public List<Cidade> listar(){
		List<Cidade> cidades = cidadeRepository.findAll();
		
		if(cidades.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Não há cidade cadastrada.");
		}
		
		return cidades;
	}
	
	public Cidade buscar(Long cidadeId) {
		Cidade cidade = cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> entidadeNaoEncontrada(CIDADE_NAO_ENCONTRADA, cidadeId));
		
		return cidade;
	}

	private EntidadeNaoEncontradaException entidadeNaoEncontrada(String mensagem, Long codigoEntidade) {
		return new EntidadeNaoEncontradaException(
				String.format(mensagem, codigoEntidade));
	}
	
	public Cidade adicionar(Cidade cidade) {
		try {
			
			Long estadoId = cidade.getEstado().getId();
			Estado estado = estadoRepository.porId(estadoId);
			return cidadeRepository.save(cidade);

			if(estado == null) {
				return new EntidadeNaoEncontradaException(
						String.format("Não há estado cadastrado com o código: %d", estadoId));
			}
		}
		
		
	}

	private void validaEstado(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		
		Estado estado = estadoRepository.porId(estadoId);
		
		if(estado == null) {
			entidadeNaoEncontrada("Não há estado cadastrado com o código: %d", estadoId);
		}
	}
	
	public Cidade alterar(Long cidadeId, Cidade cidade) {
		
		Cidade cidadeAtual = cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> entidadeNaoEncontrada(CIDADE_NAO_ENCONTRADA, cidadeId));
		
		validaEstado(cidade);
		
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		
		return cidadeRepository.save(cidadeAtual);
	}
	
	public void remover(Long cidadeId) {
		try {
			
			cidadeRepository.deleteById(cidadeId);
			
		}catch (EmptyResultDataAccessException e) {
			
			entidadeNaoEncontrada(CIDADE_NAO_ENCONTRADA, cidadeId);
		}
	}
}
