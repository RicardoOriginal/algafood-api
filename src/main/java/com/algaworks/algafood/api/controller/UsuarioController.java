package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.UsuarioInputDisassembler;
import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.model.input.SenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.model.input.UsuarioInput;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private CadastroUsuarioService service;

    @Autowired
    private UsuarioInputDisassembler disassembler;

    @Autowired
    private UsuarioModelAssembler assembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioComSenhaInput){
        final Usuario usuario = disassembler.toDomainObject(usuarioComSenhaInput);
        return assembler.toModel(service.salvar(usuario));
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId){
        final Usuario usuario = service.buscarOuFalhar(usuarioId);
        return assembler.toModel(usuario);
    }

    @GetMapping
    public List<UsuarioModel> listar(){
        return assembler.toCollectionModel(service.listar());
    }

    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInput usuarioInput){
        final Usuario usuario = service.buscarOuFalhar(usuarioId);
        disassembler.copyToDomainObject(usuarioInput, usuario);
        return assembler.toModel(service.salvar(usuario));
    }

    @PutMapping("{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senhaInput){
        service.alterarSenha(usuarioId, senhaInput);
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long usuarioId){
        service.excluir(usuarioId);
    }
}
