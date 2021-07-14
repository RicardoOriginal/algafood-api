package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.core.validation.ValidacaoException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
	
	@Autowired
	private SmartValidator validator;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(
			@RequestBody @Valid Restaurante restaurante) {
		return restauranteService.salvar(restaurante);
	}

	@GetMapping("/{restauranteId}")
	public Restaurante buscar(@PathVariable Long restauranteId) {
		return restauranteService.buscarOuFalhar(restauranteId);
	}

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteService.listar();
	}

	@PutMapping("/{restauranteId}")
	@ResponseStatus(HttpStatus.OK)
	public Restaurante alterar(@PathVariable Long restauranteId,
							   @RequestBody @Valid Restaurante restaurante) {
		restaurante.setId(restauranteId);
		return restauranteService.alterar(restauranteId, restaurante);
	}

	@PatchMapping("/{restauranteId}")
	public Restaurante alterarParcial(@PathVariable Long restauranteId,
									  @RequestBody Map<String, Object> campos,
									  HttpServletRequest request) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		merge(campos, restaurante, request);
		validate(restaurante, "restaurante");
		return restauranteService.alterar(restauranteId, restaurante);
	}
	
	private void validate(Restaurante restaurante, String objectName) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
		validator.validate(restaurante, bindingResult);
		
		if(bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}
	}

	@DeleteMapping("/{restauranteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long restauranteId){
		restauranteService.excluir(restauranteId);
	}

	private void merge(Map<String, Object> dadosOrigem,
					   Restaurante restauranteDestino, HttpServletRequest request) {

		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

		try{
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

			Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
				field.setAccessible(true);
				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

				ReflectionUtils.setField(field, restauranteDestino, novoValor);
			});
		}catch (IllegalArgumentException e){
			Throwable rootcCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootcCause, serverHttpRequest);
		}
	}
}
