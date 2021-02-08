package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável por receber requisições referentes a cidades
 *
 * @author ricardolima.ti@gmail.com
 */

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CadastroCidadeService cidadeService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody Cidade cidade){
		return cidadeService.salvar(cidade);
	}

	@GetMapping("/{cidadeId}")
	public Cidade buscar(@PathVariable Long cidadeId){
		return cidadeService.buscarOuFalhar(cidadeId);
	}

	@GetMapping
	public List<Cidade> listar(){
		return cidadeService.listar();
	}

	@PutMapping("{cidadeId}")
	@ResponseStatus(HttpStatus.OK)
	public Cidade atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade){
		return cidadeService.alterar(cidadeId, cidade);
	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId){
		cidadeService.remover(cidadeId);
	}
}
