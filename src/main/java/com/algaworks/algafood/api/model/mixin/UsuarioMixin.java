package com.algaworks.algafood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.OffsetDateTime;

public abstract class UsuarioMixin {

    @JsonIgnore
    private OffsetDateTime dataCadastro;
}
