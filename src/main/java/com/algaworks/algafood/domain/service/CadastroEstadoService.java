package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

/**
 * Serviço responsável por implementar regras de négocio relacionadas a Estado
 *
 * @author ricardolima.ti@gmail.com
 */

@Service
public class CadastroEstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado adicionar(Estado estado) {
		
		return estadoRepository.salvar(estado);
	}
	
	public List<Estado> listar(){
		List<Estado> estados = estadoRepository.todos();
		
		if(estados == null) {
			
			throw new EntidadeNaoEncontradaException("Não há nenhum estado cadastrado");
		}
		
		return estados;
	}
	
	public Estado alterar(Long estadoId, Estado estado) {
		
		Estado estadoAtual = estadoRepository.porId(estadoId);
		
		verificaEstadoVazio(estadoId, estadoAtual);
		
		estado.setId(estadoId);
		
		return estadoRepository.salvar(estado);
	}
	
	public Estado buscar(Long estadoId) {
		
		Estado estado =  estadoRepository.porId(estadoId);
		
		verificaEstadoVazio(estadoId, estado);
		
		return estado;
	}

	private void verificaEstadoVazio(Long estadoId, Estado estado) {
		if(estado == null) {
			
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe no cadastro estado com o código %d", estadoId));
		}
	}

	public void excluir(Long estadoId) {
		try {
			
			estadoRepository.remove(estadoId);
			
		}catch (EmptyResultDataAccessException e) {
			
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe no cadastro estado com o código %d", estadoId));
			
		}catch (DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException(
					String.format("Estado de código %d não pode ser removido, pois está em uso", estadoId));
		}
	}
}
