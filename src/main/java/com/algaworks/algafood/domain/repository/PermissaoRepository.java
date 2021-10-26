package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Permissao;
import org.springframework.stereotype.Repository;

/**
 * Repositório responsável por percistir dados das permissões
 *
 * @author ricardolima.ti@gmail.com
 */
@Repository
public interface PermissaoRepository extends CustomJpaRespository<Permissao, Long>{
}
