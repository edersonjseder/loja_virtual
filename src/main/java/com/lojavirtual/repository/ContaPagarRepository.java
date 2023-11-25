package com.lojavirtual.repository;

import com.lojavirtual.model.ContaPagar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContaPagarRepository extends JpaRepository<ContaPagar, Long> {
    @Query("select c from ContaPagar c where trim(c.descricao) like %:descricao%")
    List<ContaPagar> buscarContaPagarPorDescricao(@Param("descricao") String descricao);
    List<ContaPagar> findAllByPessoaId(Long pessoaId);
    List<ContaPagar> findAllByPessoaFornecedorId(Long fornecedorId);
    List<ContaPagar> findAllByEmpresaId(Long empresaId);
}
