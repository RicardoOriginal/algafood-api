package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioModel toModel(final Usuario usuario) {
        return modelMapper.map(usuario, UsuarioModel.class);
    }

    public List<UsuarioModel> toCollectionModel(final Collection<Usuario> usuarios){
        return usuarios.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
