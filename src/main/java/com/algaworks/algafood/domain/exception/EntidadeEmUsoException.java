package com.algaworks.algafood.domain.exception;

/**
 * Classe reponsável por traduzir excessoes de negócio
 *
 * @author ricardolima.ti@gmail.com
 */
public class EntidadeEmUsoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}

}
