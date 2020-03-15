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

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

/**
 * Controlador responsável por receber requisições de estado
 *
 * @author ricardolima.ti@gmail.com
 */

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private CadastroEstadoService estadoService;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		try {
			List<Estado> estados = estadoService.listar();
			return ResponseEntity.ok(estados);
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/{estadoId}")
	public ResponseEntity<?> buscar(@PathVariable Long estadoId){
		try {
			Estado estado = estadoService.buscarPor(estadoId);
			return ResponseEntity.ok(estado);
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<Estado> adicionar(@RequestBody Estado estado){
		estado = estadoService.adicionar(estado);
		return ResponseEntity.status(HttpStatus.CREATED).body(estado);
	}
	
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<?> alterar(@PathVariable Long estadoId, @RequestBody Estado estado){
		try {
			estado = estadoService.alterar(estadoId, estado);
			return ResponseEntity.ok(estado);
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> excluir(@PathVariable Long estadoId){
		try {
			estadoService.excluir(estadoId);
			return ResponseEntity.noContent().build();
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
