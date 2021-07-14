package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
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

	public static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removida pois está em uso";

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado buscarOuFalhar(Long estadoId) {
        return estadoRepository.findById(estadoId).orElseThrow(
                () -> new EstadoNaoEncontradoException(estadoId));
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado alterar(Long estadoId, Estado estado) {
        Estado estadoAtual = buscarOuFalhar(estadoId);
        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return estadoRepository.save(estadoAtual);
    }

    public void excluir(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);

        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(estadoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ESTADO_EM_USO, estadoId));
        }
    }
}
