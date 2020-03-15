package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Estado;

/**
 * Repositório responsável por persistir dados de estado
 *
 * @author ricardolima.ti@gmail.com
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{
}
