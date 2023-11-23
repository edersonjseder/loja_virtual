package com.lojavirtual.repository;

import com.lojavirtual.model.CategoriaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long> {
    @Query(value = "select count(c.id) > 0 from CategoriaProduto c where c.nome = :nome")
    boolean existeCategoria(@Param("nome") String nome);

    Optional<CategoriaProduto> findCategoriaProdutoByNome(String nome);

    @Query(value = "select c from CategoriaProduto c where c.descricao ilike %:descricao%")
    List<CategoriaProduto> buscarCategoriaProdutoPorDescricao(@Param("descricao") String descricao);
}
