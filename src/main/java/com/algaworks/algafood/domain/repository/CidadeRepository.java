package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cidade;

/**
 * Classe responsável por persistir dados de cidade
 *
 * @author ricardolima.ti@gmail.com
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
}
