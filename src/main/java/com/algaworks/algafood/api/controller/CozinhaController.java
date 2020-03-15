/**
 * 
 */
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
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

/**
 * Controlador responsável por receber requisições de cozinhas
 *
 * @author ricardolima.ti@gmail.com
 */

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CadastroCozinhaService cozinhaService;

	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaService.listar();
	}

	@GetMapping("/{cozinhaId}")
	public ResponseEntity<?> buscar(@PathVariable Long cozinhaId) {
		try {
			Cozinha cozinha = cozinhaService.buscarPor(cozinhaId);
			return ResponseEntity.ok(cozinha);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
		cozinha = cozinhaService.salvar(cozinha);
		return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);
	}

	@PutMapping("/{cozinhaId}")
	public ResponseEntity<?> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
		try {
			cozinha = cozinhaService.alterar(cozinhaId, cozinha);
			return ResponseEntity.ok(cozinha);
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<?> remover(@PathVariable Long cozinhaId) {
		try {
			cozinhaService.excluir(cozinhaId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
