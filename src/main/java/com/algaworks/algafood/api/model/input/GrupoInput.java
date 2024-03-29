package com.algaworks.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class GrupoInput {

    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private List<PermissaoIdInput> permissoes;

}
