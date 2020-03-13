package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

/**
 * Controlador responsável por receber requisições referentes a cidades
 *
 * @author ricardolima.ti@gmail.com
 */

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		try {
			
			List<Cidade> cidades = cadastroCidade.listar();
			
			return ResponseEntity.ok(cidades);
			
		}catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/{cidadeId}")
	public ResponseEntity<?> buscar(@PathVariable Long cidadeId){
		try {
			
			Cidade cidade = cadastroCidade.buscar(cidadeId);
			
			return ResponseEntity.ok(cidade);
			
		}catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cidade cidade){
		
		try{
			
			cidade = cadastroCidade.adicionar(cidade);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
			
		}catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("{cidadeId}")
	public ResponseEntity<?> alterar(@PathVariable Long cidadeId, 
			@RequestBody Cidade cidade){		
		try {
			
			cidade = cadastroCidade.alterar(cidadeId, cidade);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
			
		}catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<?> remover(@PathVariable Long cidadeId){
		try {
			
			cadastroCidade.remover(cidadeId);
			
			return ResponseEntity.noContent().build();
			
		}catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
