package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;


/**
 * Interfase responsável por transacionar cozinhas
 *
 * @author ricardolima.ti@gmail.com
 */
@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{
	
	List<Cozinha> findTodasByNome(String nome);
	
	Cozinha findByNome(String nome);
}
