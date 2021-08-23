package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;

	@Autowired
	private EstadoModelAssembler estadoModelAssembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput){
		final Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
		return estadoModelAssembler.toModel(estadoService.salvar(estado));
	}

	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable Long estadoId){
		return estadoModelAssembler.toModel(estadoService.buscarOuFalhar(estadoId));
	}

	@GetMapping
	public List<EstadoModel> listar(){
		return estadoModelAssembler.toCollectionModel(estadoService.listar());
	}

	@PutMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.OK)
	public EstadoModel alterar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput){
		try {
			final Estado estado = estadoService.buscarOuFalhar(estadoId);
			estadoInputDisassembler.copyToDomainObject(estadoInput, estado);
			return estadoModelAssembler.toModel(estadoService.salvar(estado));
		}catch (EstadoNaoEncontradoException e){
			throw new NegocioException(e.getMessage());
		}
	}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long estadoId){
		estadoService.excluir(estadoId);
	}
}
