package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.input.ProdutoInput;
import com.algaworks.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissaoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Produto toDomainObject(final ProdutoInput produtoInput){
        return modelMapper.map(produtoInput, Produto.class);
    }

    public void copyToDomainObject(final ProdutoInput produtoInput, final Produto produto){
        modelMapper.map(produtoInput, produto);
    }
}
