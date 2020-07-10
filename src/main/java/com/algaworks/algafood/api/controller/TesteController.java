package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("cozinhas/por-nome")
	public ResponseEntity<List<Cozinha>> consultaPorNome(String nome){
		return ResponseEntity.ok(cozinhaRepository.findTodasByNome(nome));
	}
	
	@GetMapping("cozinhas/unico-por-nome")
	public ResponseEntity<Cozinha> buscaPorNome(String nome){
		return ResponseEntity.ok(cozinhaRepository.findByNome(nome));
	}
	
	@GetMapping("cozinhas/por-nome-contendo")
	public ResponseEntity<List<Cozinha>> buscaPorNomeContendo(String nome){
		return ResponseEntity.ok(cozinhaRepository.findTodasByNomeContaining(nome));
	}
	
	@GetMapping("cozinhas/exists")
	public boolean cozinhaExists(String nome){
		return cozinhaRepository.existsByNome(nome);
	}
	
	@GetMapping("restaurantes/por-taxa-frete")
	public ResponseEntity<List<Restaurante>> restaurantesPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal){
		return ResponseEntity.ok(restauranteRepository.queryByTaxaFreteBetween(taxaInicial, taxaFinal));
	}
	
	@GetMapping("restaurantes/por-nome")
	public ResponseEntity<List<Restaurante>> restaurantesPorNomeECozinhaId(String nome, Long cozinhaId){
		return ResponseEntity.ok(restauranteRepository.consultarPorNome(nome, cozinhaId));
	}
	
	@GetMapping("restaurantes/primeiro-por-nome")
	public Optional<Restaurante> restaurantesPrimeirPorNome(String nome){
		return restauranteRepository.findFirtsRestauranteByNomeContaining(nome);
	}
	
	@GetMapping("restaurantes/count-por-cozinha")
	public int restaurantesCountPorCozinha(Long cozinhaId){
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}
}
