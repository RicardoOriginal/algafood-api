package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
	
	public List<Estado> listar(){
		List<Estado> estados = estadoRepository.findAll();
		if(estados == null) {
			throw new EntidadeNaoEncontradaException("Não há estado cadastrado");
		}
		return estados;
	}
	
	public Estado buscarPor(Long estadoId) {
		return estadoRepository.findById(estadoId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(
						String.format("Não há estado cadastrado com o código: %d", estadoId)));
	}
	
	public Estado adicionar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public Estado alterar(Long estadoId, Estado estado) {
		Estado estadoAtual = buscarPor(estadoId);
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		return estadoRepository.save(estadoAtual);
	}
	
	public void excluir(Long estadoId) {
		buscarPor(estadoId);
		try {
			estadoRepository.deleteById(estadoId);
			
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de código %d não pode ser removido, pois está em uso.", estadoId));
		}
	}
}
