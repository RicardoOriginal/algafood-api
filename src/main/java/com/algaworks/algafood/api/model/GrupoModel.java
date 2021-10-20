package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GrupoModel {
    private Long id;
    private String nome;
    private List<PermissaoModel> permissoes;
}
