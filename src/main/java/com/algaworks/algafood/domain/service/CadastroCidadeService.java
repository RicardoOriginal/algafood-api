package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<Cidade> listar(){
		List<Cidade> cidades = cidadeRepository.findAll();
		if(cidades.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Não há cidade cadastrada.");
		}
		return cidades;
	}
	
	public Cidade buscarPor(Long cidadeId) {
		return cidadeRepository.findById(cidadeId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(
						String.format("Não há cidade cadastrada com o código: %d", cidadeId)));
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
		cidadeRepository.deleteById(cidadeId);
	}
}
