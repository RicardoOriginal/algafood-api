package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

/**
 * Serviço responsável por implementar regras de negócio de cozinhas
 *
 * @author ricardolima.ti@gmail.com
 */

@Service
public class CadastroCozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public List<Cozinha> listar(){
		return cozinhaRepository.findAll();
	}
	
	public Cozinha buscarPor(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(
						String.format("Não há cozinha no cadastro com o código: %d",cozinhaId)));
	}
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public Cozinha alterar(Long cozinhaId, Cozinha cozinha) {
		Cozinha cozinhaAtual = buscarPor(cozinhaId);
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		return salvar(cozinhaAtual);
	}
	
	public void excluir(Long cozinhaId) {
		buscarPor(cozinhaId);
		try {
			cozinhaRepository.deleteById(cozinhaId);
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cozinha de código %d não pode ser removida pois está em uso", cozinhaId));
		}
	}
}
