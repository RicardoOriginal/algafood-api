package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controlador responsável por receber requisições de cozinhas
 *
 * @author ricardolima.ti@gmail.com
 */

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CadastroCozinhaService cozinhaService;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        try {
            final Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
            return cozinhaModelAssembler.toModel(cozinhaService.salvar(cozinha));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable Long cozinhaId) {
        return cozinhaModelAssembler.toModel(cozinhaService.buscarOuFalhar(cozinhaId));
    }

    @GetMapping
    public Page<CozinhaModel> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhasPage = cozinhaService.listar(pageable);
        List<CozinhaModel> cozinhaModels = cozinhaModelAssembler.toCollectionModel(cozinhasPage.getContent());
        Page<CozinhaModel> cozinhaModelPage = new PageImpl<>(cozinhaModels, pageable, cozinhasPage.getTotalElements());
        return cozinhaModelPage;
    }

    @PutMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.OK)
    public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
        try {
            final Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(cozinhaId);
            cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
            return cozinhaModelAssembler.toModel(cozinhaService.salvar(cozinhaAtual));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cozinhaService.excluir(cozinhaId);
    }
}
