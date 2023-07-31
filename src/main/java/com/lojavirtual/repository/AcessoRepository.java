package com.lojavirtual.repository;

import com.lojavirtual.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AcessoRepository extends JpaRepository<Acesso, Long> {
    @Query("select a from Acesso a where upper(trim(a.descricao)) like %?1%")
    Optional<List<Acesso> >carregarAcessosPorDescricao(String desc);

    @Query("select a from Acesso a where upper(trim(a.descricao)) = ?1")
    Optional<Acesso> carregarAcessoPorDescricao(String desc);

}
