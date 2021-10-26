package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.GrupoSemPermissaoModel;
import com.algaworks.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoSemPermissaoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoSemPermissaoModel toModel(final Grupo grupo) {
        return modelMapper.map(grupo, GrupoSemPermissaoModel.class);
    }

    public List<GrupoSemPermissaoModel> toCollectionModel(final Collection<Grupo> grupos){
        return grupos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
