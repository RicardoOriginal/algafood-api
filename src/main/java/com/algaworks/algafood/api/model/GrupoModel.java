package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GrupoModel extends GrupoSemPermissaoModel{
    private List<PermissaoModel> permissoes;
}
