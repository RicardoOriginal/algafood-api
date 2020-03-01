package com.algaworks.algafood.api.modelo;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NonNull;

/**
 * Classe responsável por customizar a representação de dados de cozinha em formato XML
 *
 * @author ricardolima.ti@gmail.com
 */
@Data
@JacksonXmlRootElement(localName = "Cozinhas")
public class CozinhaXmlWrapper {

	@JsonProperty("cozinha")
	@JacksonXmlElementWrapper(useWrapping = false )
	@NonNull
	private List<Cozinha> cozinhas;
	
}
