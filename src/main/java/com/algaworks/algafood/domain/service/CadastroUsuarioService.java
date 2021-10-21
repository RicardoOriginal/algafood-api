package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.api.model.input.SenhaInput;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario){
        return usuario;
    }

    @Transactional
    public void alterarSenha(Long usuarioId, SenhaInput senhaInput){
        Usuario usuario = this.buscarOuFalhar(usuarioId);
        if(usuario.senhaNaoCoincideCom(senhaInput.getSenhaAtual())){
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }
        usuario.setSenha(senhaInput.getNovaSenha());
        this.salvar(usuario);
    }

    public Usuario buscarOuFalhar(Long usuarioId){
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }


    @Transactional
    public void excluir(Long usuarioId){
        try {
            usuarioRepository.deleteById(usuarioId);
            usuarioRepository.flush();

        }catch (EmptyResultDataAccessException e){
            throw new UsuarioNaoEncontradoException(e.getMessage());

        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(e.getMessage());
        }
    }
}
