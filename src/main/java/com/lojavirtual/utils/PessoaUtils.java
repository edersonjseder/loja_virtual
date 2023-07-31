package com.lojavirtual.utils;

import com.lojavirtual.dto.PessoaFisicaDTO;
import com.lojavirtual.dto.PessoaJuridicaDTO;
import com.lojavirtual.dto.PessoaResponseDto;
import com.lojavirtual.model.PessoaFisica;
import com.lojavirtual.model.PessoaJuridica;
import com.lojavirtual.response.TokenResponse;
import com.lojavirtual.response.UserTokenResponse;
import org.springframework.stereotype.Component;

@Component
public class PessoaUtils {
    public PessoaFisica fromPessoaFisicaDto(PessoaFisicaDTO pessoaFisicaDTO) {
        return PessoaFisica.builder()
                .cpf(pessoaFisicaDTO.getCpf())
                .dataNascimento(pessoaFisicaDTO.getDataNascimento())
                .nome(pessoaFisicaDTO.getNome())
                .email(pessoaFisicaDTO.getEmail())
                .telefone(pessoaFisicaDTO.getTelefone())
                .tipoPessoa(pessoaFisicaDTO.getTipoPessoa())
                .build();
    }

    public PessoaFisicaDTO toPessoaFisicaDto(PessoaFisica pessoaFisica) {
        return PessoaFisicaDTO.builder()
                .cpf(pessoaFisica.getCpf())
                .dataNascimento(pessoaFisica.getDataNascimento())
                .nome(pessoaFisica.getNome())
                .email(pessoaFisica.getEmail())
                .telefone(pessoaFisica.getTelefone())
                .tipoPessoa(pessoaFisica.getTipoPessoa())
                .enderecos(pessoaFisica.getEnderecos())
                .build();
    }

    public PessoaJuridica fromPessoaJuridicaDto(PessoaJuridicaDTO pessoaJuridicaDTO) {
        return PessoaJuridica.builder()
                .cnpj(pessoaJuridicaDTO.getCnpj())
                .inscricaoEstadual(pessoaJuridicaDTO.getInscricaoEstadual())
                .categoria(pessoaJuridicaDTO.getCategoria())
                .inscricaoMunicipal(pessoaJuridicaDTO.getInscricaoMunicipal())
                .nomeFantasia(pessoaJuridicaDTO.getNomeFantasia())
                .razaoSocial(pessoaJuridicaDTO.getRazaoSocial())
                .email(pessoaJuridicaDTO.getEmail())
                .tipoPessoa(pessoaJuridicaDTO.getTipoPessoa())
                .nome(pessoaJuridicaDTO.getNome())
                .telefone(pessoaJuridicaDTO.getTelefone())
                .build();
    }

    public PessoaJuridicaDTO toPessoaJuridicaDto(PessoaJuridica pessoaJuridica) {
        return PessoaJuridicaDTO.builder()
                .cnpj(pessoaJuridica.getCnpj())
                .inscricaoEstadual(pessoaJuridica.getInscricaoEstadual())
                .categoria(pessoaJuridica.getCategoria())
                .inscricaoMunicipal(pessoaJuridica.getInscricaoMunicipal())
                .nomeFantasia(pessoaJuridica.getNomeFantasia())
                .razaoSocial(pessoaJuridica.getRazaoSocial())
                .email(pessoaJuridica.getEmail())
                .tipoPessoa(pessoaJuridica.getTipoPessoa())
                .nome(pessoaJuridica.getNome())
                .telefone(pessoaJuridica.getTelefone())
                .enderecos(pessoaJuridica.getEnderecos())
                .build();
    }

    public PessoaResponseDto toPessoaResponsePfDto(PessoaFisica pessoa, UserTokenResponse tokenResponse) {
        return PessoaResponseDto.builder()
                .cpfCnpj(pessoa.getCpf())
                .nome(pessoa.getNome())
                .email(pessoa.getEmail())
                .username(tokenResponse.getUsername())
                .dataAtualSenha(tokenResponse.getDataAtualSenha())
                .tipoPessoa(pessoa.getTipoPessoa())
                .build();
    }

    public PessoaResponseDto toPessoaResponsePjDto(PessoaJuridica pessoa, UserTokenResponse tokenResponse) {
        return PessoaResponseDto.builder()
                .cpfCnpj(pessoa.getCnpj())
                .nome(pessoa.getNome())
                .email(pessoa.getEmail())
                .username(tokenResponse.getUsername())
                .dataAtualSenha(tokenResponse.getDataAtualSenha())
                .tipoPessoa(pessoa.getTipoPessoa())
                .build();
    }
}
