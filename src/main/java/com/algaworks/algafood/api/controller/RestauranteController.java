package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Controller responsável por receber requisições referentes a restaurantes
 *
 * @author ricardolima.ti@gmail.com
 */

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private CadastroRestauranteService restauranteService;
	
	@GetMapping
	public ResponseEntity<List<Restaurante>> listar(){
		
		List<Restaurante> restaurantes = restauranteService.todos();
		
		if(!restaurantes.isEmpty()) {
			
			return ResponseEntity.ok(restaurantes);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId){
		
		Restaurante restaurante = restauranteService.buscar(restauranteId);
		
		if(restaurante != null) {
			
			return ResponseEntity.ok(restaurante); 
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
		
		try{
			
			restaurante = restauranteService.salvar(restaurante);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
			
		}catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> alterar(@PathVariable Long restauranteId, 
			@RequestBody Restaurante restaurante){
		
		Restaurante restauranteAtual = restauranteService.buscar(restauranteId);
		
		if(restauranteAtual == null) {
			
			return ResponseEntity.notFound().build();
		}
		
		try {
			
			restaurante.setId(restauranteId);
			
			restaurante = restauranteService.alterar(restaurante);
			
			return ResponseEntity.ok(restaurante);
			
		}catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> alterarParcial(@PathVariable Long restauranteId,
			@RequestBody Map<String, Object> campos){
		
		Restaurante restaurante = restauranteService.buscar(restauranteId);
		
		if(restaurante == null) {
			return ResponseEntity.notFound().build();
		}
		
		merge(campos, restaurante);
		
		return alterar(restauranteId, restaurante);
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
		
		dadosOrigem.forEach((nomePropriedade, valorPropriedade)->{
			
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}
}
