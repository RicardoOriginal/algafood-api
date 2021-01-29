package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável por implementar regras de négocio relacionadas a Estado
 *
 * @author ricardolima.ti@gmail.com
 */

@Service
public class CadastroEstadoService {

	@Autowired
    private EstadoRepository estadoRepository;

	public static final String MSG_ESTADO_NAO_ENCONTRADA = "Não há estado cadastrado com o código: %d";
	public static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removida pois está em uso";

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado buscarPor(Long estadoId) {
        return estadoRepository.findById(estadoId).orElseThrow(
                () -> new EntidadeNaoEncontradaException(
                        String.format(MSG_ESTADO_NAO_ENCONTRADA, estadoId)));
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

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_ESTADO_NAO_ENCONTRADA, estadoId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ESTADO_EM_USO, estadoId));
        }
    }
}
