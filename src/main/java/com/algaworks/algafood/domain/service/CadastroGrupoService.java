package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CadastroGrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroPermissaoService cadastroPermissaoService;

    @Transactional
    public Grupo salvar(Grupo grupo){
        return grupoRepository.save(grupo);
    }

    public Grupo buscarOuFalhar(Long grupoId){
        return grupoRepository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
    }

    public List<Grupo> listar(){
        return grupoRepository.findAll();
    }

    @Transactional
    public void exluir(Long grupoId){
        try{
            grupoRepository.deleteById(grupoId);
            grupoRepository.flush();
        }catch (EmptyResultDataAccessException e){
            throw new GrupoNaoEncontradoException(grupoId);

        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("Grupo de código %d não pode ser excluído pois está em uso.", grupoId));
        }
    }

    @Transactional
    public void desassociarPermissao(Long grupoId, Long permissaoId){
        final Grupo grupo = buscarOuFalhar(grupoId);
        final Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);
        grupo.removerPermissao(permissao);
    }

    @Transactional
    public void associarPermissao(Long grupoId, Long permissaoId){
        final Grupo grupo = buscarOuFalhar(grupoId);
        final Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);
        grupo.adicionarPermissao(permissao);
    }
}