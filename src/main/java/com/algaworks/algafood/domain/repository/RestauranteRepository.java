package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;


/**
 * Interfase responsável por percistir dados de restaurantes
 *
 * @author ricardolima.ti@gmail.com
 */
@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>{
}
