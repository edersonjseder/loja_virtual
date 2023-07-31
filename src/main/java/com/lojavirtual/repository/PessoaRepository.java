package com.lojavirtual.repository;

import com.lojavirtual.model.Pessoa;
import com.lojavirtual.model.PessoaFisica;
import com.lojavirtual.model.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query(value = "select pf from PessoaFisica pf where pf.email = ?1")
    Optional<Pessoa> buscarPessoaFisicaPorEmail(String email);
    @Query(value = "select pj from PessoaJuridica pj where pj.email = ?1")
    Optional<Pessoa> buscarPessoaJuridicaPorEmail(String email);
    @Query(value = "select pf from PessoaFisica pf where pf.cpf = ?1")
    PessoaFisica verificarCpfCadastrado(String cpf);
    @Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
    PessoaJuridica verificarCnpjCadastrado(String cnpj);
    @Query(value = "select pj from PessoaJuridica pj where pj.inscricaoEstadual = ?1")
    PessoaJuridica verificarInsEstadualCadastrado(String ie);
    @Query(value = "select pj from PessoaJuridica pj where pj.inscricaoMunicipal = ?1")
    PessoaJuridica verificarInsMunicipalCadastrado(String ie);
}
