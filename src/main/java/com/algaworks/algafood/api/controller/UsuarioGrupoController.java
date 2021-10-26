package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.GrupoSemPermissaoModelAssembler;
import com.algaworks.algafood.api.model.GrupoSemPermissaoModel;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private GrupoSemPermissaoModelAssembler grupoSemPermissaoModelAssembler;

    @GetMapping
    public List<GrupoSemPermissaoModel> listarGrupos(@PathVariable Long usuarioId){
        final Set<Grupo> grupos = cadastroUsuarioService.listarGrupos(usuarioId);
        return grupoSemPermissaoModelAssembler.toCollectionModel(grupos);
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        cadastroUsuarioService.associarGrupo(usuarioId, grupoId);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        cadastroUsuarioService.desassociarGrupo(usuarioId, grupoId);
    }
}
