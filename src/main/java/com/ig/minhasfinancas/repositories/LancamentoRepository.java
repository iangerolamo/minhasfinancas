package com.ig.minhasfinancas.repositories;

import com.ig.minhasfinancas.entities.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
