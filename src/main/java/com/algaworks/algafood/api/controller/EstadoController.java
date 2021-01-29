package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado estado){
		return estadoService.adicionar(estado);
	}

	@GetMapping("/{estadoId}")
	public Estado buscar(@PathVariable Long estadoId){
		return estadoService.buscarPor(estadoId);
	}

	@GetMapping
	public List<Estado> listar(){
		return estadoService.listar();
	}

	@PutMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.OK)
	public Estado alterar(@PathVariable Long estadoId, @RequestBody Estado estado){
		return estadoService.alterar(estadoId, estado);
	}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long estadoId){
		estadoService.excluir(estadoId);
	}
}
