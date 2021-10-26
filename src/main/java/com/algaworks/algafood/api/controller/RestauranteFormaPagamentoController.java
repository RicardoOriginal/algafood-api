package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Controller responsável por receber requisições referentes a restaurantes
 *
 * @author ricardolima.ti@gmail.com
 */

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	@GetMapping
	public List<FormaPagamentoModel> listar(@PathVariable Long restauranteId){
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		return formaPagamentoModelAssembler.toCollectionModel(restaurante.getFormasPagamento());
	}

	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable long restauranteId, @PathVariable Long formaPagamentoId){
		cadastroRestauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
	}

	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable long restauranteId, @PathVariable Long formaPagamentoId){
		cadastroRestauranteService.resassociarFormaPagamento(restauranteId, formaPagamentoId);
	}
}
