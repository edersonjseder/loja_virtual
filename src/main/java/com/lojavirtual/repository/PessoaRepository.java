package com.lojavirtual.repository;

import com.lojavirtual.model.Pessoa;
import com.lojavirtual.model.PessoaFisica;
import com.lojavirtual.model.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    /* Pesquisa por nome PF */
    @Query(value = "select pf from PessoaFisica pf where trim(pf.nome) ilike %?1%")
    List<PessoaFisica> buscarPorNomePf(String nome);

    /* Pesquisa por nome PJ */
    @Query(value = "select pj from PessoaJuridica pj where trim(pj.nome) ilike %?1%")
    List<PessoaJuridica> buscarPorNomePj(String nome);

    /* Pesquisa por email PF */
    @Query(value = "select pf from PessoaFisica pf where pf.email = ?1")
    Optional<Pessoa> buscarPessoaFisicaPorEmail(String email);

    /* Pesquisa por email PJ */
    @Query(value = "select pj from PessoaJuridica pj where pj.email = ?1")
    Optional<Pessoa> buscarPessoaJuridicaPorEmail(String email);

    /* Pesquisa por CPF PF */
    @Query(value = "select pf from PessoaFisica pf where pf.cpf = ?1")
    PessoaFisica verificarCpfCadastrado(String cpf);

    /* Pesquisa por CNPJ PJ */
    @Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
    PessoaJuridica verificarCnpjCadastrado(String cnpj);

    @Query(value = "select pj from PessoaJuridica pj where pj.inscricaoEstadual = ?1")
    PessoaJuridica verificarInsEstadualCadastrado(String ie);

    @Query(value = "select pj from PessoaJuridica pj where pj.inscricaoMunicipal = ?1")
    PessoaJuridica verificarInsMunicipalCadastrado(String ie);
}
