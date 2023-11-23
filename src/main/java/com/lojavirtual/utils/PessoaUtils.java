package com.lojavirtual.utils;

import com.lojavirtual.dto.PessoaFisicaDTO;
import com.lojavirtual.dto.PessoaJuridicaDTO;
import com.lojavirtual.dto.PessoaResponseDto;
import com.lojavirtual.model.Pessoa;
import com.lojavirtual.model.PessoaFisica;
import com.lojavirtual.model.PessoaJuridica;
import com.lojavirtual.response.UserTokenResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PessoaUtils {
    public PessoaFisica toPessoaFisica(PessoaFisicaDTO pessoaFisicaDTO) {
        return PessoaFisica.builder()
                .id(pessoaFisicaDTO.getId())
                .cpf(pessoaFisicaDTO.getCpf())
                .dataNascimento(pessoaFisicaDTO.getDataNascimento())
                .nome(pessoaFisicaDTO.getNome())
                .email(pessoaFisicaDTO.getEmail())
                .telefone(pessoaFisicaDTO.getTelefone())
                .tipoPessoa(pessoaFisicaDTO.getTipoPessoa())
                .empresa(toPessoaJuridica(pessoaFisicaDTO.getEmpresa()))
                .build();
    }

    public PessoaFisicaDTO toPessoaFisicaDto(PessoaFisica pessoaFisica) {
        return PessoaFisicaDTO.builder()
                .id(pessoaFisica.getId())
                .cpf(pessoaFisica.getCpf())
                .dataNascimento(pessoaFisica.getDataNascimento())
                .nome(pessoaFisica.getNome())
                .email(pessoaFisica.getEmail())
                .telefone(pessoaFisica.getTelefone())
                .tipoPessoa(pessoaFisica.getTipoPessoa())
                .empresa(toPessoaJuridicaDto((PessoaJuridica) pessoaFisica.getEmpresa()))
                .enderecos(pessoaFisica.getEnderecos())
                .build();
    }

    public List<PessoaFisicaDTO> toListPessoaFisicaDto(List<PessoaFisica> pessoaFisicaList) {
        return pessoaFisicaList.stream().map(pessoaFisica -> PessoaFisicaDTO.builder()
                .id(pessoaFisica.getId())
                .cpf(pessoaFisica.getCpf())
                .dataNascimento(pessoaFisica.getDataNascimento())
                .nome(pessoaFisica.getNome())
                .email(pessoaFisica.getEmail())
                .telefone(pessoaFisica.getTelefone())
                .tipoPessoa(pessoaFisica.getTipoPessoa())
                .empresa(toPessoaJuridicaDto((PessoaJuridica) pessoaFisica.getEmpresa()))
                .enderecos(pessoaFisica.getEnderecos())
                .build()).collect(Collectors.toList());
    }

    public PessoaJuridica toPessoaJuridica(PessoaJuridicaDTO pessoaJuridicaDTO) {
        return PessoaJuridica.builder()
                .id(pessoaJuridicaDTO.getId())
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
                .empresa(pessoaJuridicaDTO.getEmpresa())
                .build();
    }

    public PessoaJuridicaDTO toPessoaJuridicaDto(PessoaJuridica pessoaJuridica) {
        return PessoaJuridicaDTO.builder()
                .id(pessoaJuridica.getId())
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

    public List<PessoaJuridicaDTO> toListPessoaJuridicaDto(List<PessoaJuridica> pessoaJuridicaList) {
        return pessoaJuridicaList.stream().map(pessoaJuridica -> PessoaJuridicaDTO.builder()
                .id(pessoaJuridica.getId())
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
                .build()).collect(Collectors.toList());
    }

    public PessoaResponseDto toPessoaResponseDto(Pessoa pessoa, UserTokenResponse tokenResponse) {
        if (pessoa instanceof PessoaFisica) {
            return PessoaResponseDto.builder()
                    .cpfCnpj(((PessoaFisica)pessoa).getCpf())
                    .nome(pessoa.getNome())
                    .email(pessoa.getEmail())
                    .username(tokenResponse != null ? tokenResponse.getUsername() : "")
                    .dataAtualSenha(tokenResponse != null ? tokenResponse.getDataAtualSenha() : "")
                    .tipoPessoa(pessoa.getTipoPessoa())
                    .token(tokenResponse != null ? tokenResponse.getAuthorization() : "")
                    .build();
        } else {
            return PessoaResponseDto.builder()
                    .cpfCnpj(((PessoaJuridica)pessoa).getCnpj())
                    .nome(pessoa.getNome())
                    .email(pessoa.getEmail())
                    .username(tokenResponse != null ? tokenResponse.getUsername() : "")
                    .dataAtualSenha(tokenResponse != null ? tokenResponse.getDataAtualSenha() : "")
                    .tipoPessoa(pessoa.getTipoPessoa())
                    .token(tokenResponse != null ? tokenResponse.getAuthorization() : "")
                    .build();
        }
    }
}
