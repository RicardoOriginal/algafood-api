package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.PedidoInputDisassembler;
import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.filter.PedidoFilter;
import com.algaworks.algafood.domain.service.CadastroPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final CadastroPedidoService cadastroPedidoService;
    private final PedidoInputDisassembler pedidoInputDisassembler;
    private final PedidoModelAssembler pedidoModelAssembler;
    private final PedidoResumoModelAssembler pedidoResumoModelAssembler;


    @GetMapping
    public Page<PedidoResumoModel> listar(@PageableDefault Pageable pageable, PedidoFilter filter){
        Page<Pedido> pedidoPage = cadastroPedidoService.listar(pageable, filter);
        List<PedidoResumoModel> pedidosResumoModel = pedidoResumoModelAssembler .toCollectionModel(pedidoPage.getContent());
        return new PageImpl<>(pedidosResumoModel, pageable, pedidoPage.getTotalElements());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
        try {
            Pedido pedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
            pedido.setCliente(new Usuario());
            pedido.getCliente().setId(1L);
            pedido = cadastroPedidoService.emitir(pedido);
            return pedidoModelAssembler.toModel(pedido);
        }catch (Exception e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @GetMapping("/{pedidoId}")
    public PedidoModel buscar(@PathVariable Long pedidoId){
        final Pedido pedido = cadastroPedidoService.buscarOuFalhar(pedidoId);
        return pedidoModelAssembler.toModel(pedido);
    }
}
