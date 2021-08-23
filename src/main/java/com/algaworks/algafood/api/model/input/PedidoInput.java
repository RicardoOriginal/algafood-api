package com.algaworks.algafood.api.model.input;

import com.algaworks.algafood.domain.model.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoInput {
    private Long id;
    private BigDecimal subTotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private Endereco enderecoEntrega;
    private StatusPedido status;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;
    private FormaPagamento formaPagamento;
    private Restaurante restaurante;
    private Usuario cliente;
    private List<ItemPedido> itens = new ArrayList<>();
}
