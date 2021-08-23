package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	public static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida pois está em uso";

	@Transactional
	public Cidade salvar(Cidade cidade) {
		try {
			Estado estado = estadoService.buscarOuFalhar(cidade.getEstado().getId());
			cidade.setEstado(estado);
		}catch (EstadoNaoEncontradoException e){
			throw new NegocioException(e.getMessage(), e);
		}
		return cidadeRepository.save(cidade);
	}

	public Cidade buscarOuFalhar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId).orElseThrow(
				() -> new CidadeNaoEncontradaException(cidadeId));
	}

	public List<Cidade> listar(){
		return cidadeRepository.findAll();
	}

	@Transactional
	public Cidade alterar(Long cidadeId, Cidade cidade) {
		Cidade cidadeAtual = buscarOuFalhar(cidadeId);
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		return salvar(cidadeAtual);
	}

	@Transactional
	public void remover(Long cidadeId) {
		try{
			cidadeRepository.deleteById(cidadeId);

		}catch (EmptyResultDataAccessException e ){
			throw new CidadeNaoEncontradaException(cidadeId);

		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}
}
