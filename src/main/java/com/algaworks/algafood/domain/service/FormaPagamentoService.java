package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public static final String MSG_FORMA_PAGAMENTO_EM_USO = "Forma de pagamento de código %d não pode ser removida pois está em uso";

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento){
        return formaPagamentoRepository.save(formaPagamento);
    }

    public FormaPagamento buscarOuFalhar(Long formaDePagamentoId){
        return formaPagamentoRepository.findById(formaDePagamentoId).orElseThrow(
                () -> new NegocioException("Forma de pagamento não encontrada"));
    }

    public List<FormaPagamento> listar(){
        return formaPagamentoRepository.findAll();
    }

    @Transactional
    public void excluir(Long formaDePagamentoId){
        try {
            formaPagamentoRepository.deleteById(formaDePagamentoId);
            formaPagamentoRepository.flush();
        }catch (EmptyResultDataAccessException e ){
            throw new CozinhaNaoEncontradaException(formaDePagamentoId);

        }catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_FORMA_PAGAMENTO_EM_USO, formaDePagamentoId));
        }
    }
}
