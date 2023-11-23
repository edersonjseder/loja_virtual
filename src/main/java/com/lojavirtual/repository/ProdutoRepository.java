package com.lojavirtual.repository;

import com.lojavirtual.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findProdutoByNome(String nome);
    List<Produto> findProdutoByDescricao(String descricao);
    Produto findProdutoByModelo(String modelo);
    Produto findProdutoByModeloAndEmpresaId(String modelo, Long empresaId);
    @Query(value = "select count(p.id) > 0 from Produto p where p.modelo = :modelo and p.empresa.id = :idEmpresa")
    boolean existeProduto(@Param("modelo") String modelo, @Param("idEmpresa") Long idEmpresa);
    @Query(value = "select count(p.id) > 0 from Produto p where p.modelo = :modelo")
    boolean existeModeloProduto(@Param("modelo") String modelo);
}
