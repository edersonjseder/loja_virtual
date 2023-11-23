package com.lojavirtual.repository;

import com.lojavirtual.model.MarcaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarcaProdutoRepository extends JpaRepository<MarcaProduto, Long> {
    @Query(value = "select count(c.id) > 0 from MarcaProduto c where c.nome = :nome")
    boolean existeMarca(@Param("nome") String nome);

    Optional<MarcaProduto> findMarcaProdutoByNome(String nome);

    @Query(value = "select c from MarcaProduto c where c.descricao ilike %:descricao%")
    List<MarcaProduto> buscarMarcaProdutoPorDescricao(@Param("descricao") String descricao);
}
