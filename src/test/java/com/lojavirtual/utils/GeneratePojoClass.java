package com.lojavirtual.utils;

import com.lojavirtual.dto.PessoaFisicaDTO;
import com.lojavirtual.dto.PessoaJuridicaDTO;
import com.lojavirtual.enums.TipoEndereco;
import com.lojavirtual.model.Endereco;
import com.lojavirtual.model.PessoaJuridica;
import com.lojavirtual.security.request.SignUpRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GeneratePojoClass {
    public static PessoaFisicaDTO genPessoaFisica() {
        var pesJur = PessoaJuridicaDTO.builder()
                .cnpj("30.388.123/0001-02")
                .inscricaoEstadual("8520.4563.1203")
                .categoria("Eletronicos")
                .inscricaoMunicipal("99632.00214")
                .nomeFantasia("Jacinda Manoel Eletronicos")
                .razaoSocial("JaMa Eletronicos")
                .email("jama@gmail.com")
                .tipoPessoa("PJ")
                .nome("Jacinda Manoel")
                .telefone("92996874520")
                .build();

        var pessoaFisica = PessoaFisicaDTO.builder()
                .cpf("133.494.840-26")
                .dataNascimento(parseDate())
                .nome("Joao Aguirre")
                .email("joaoaguirre@gmail.com")
                .telefone("92985207670")
                .tipoPessoa("PF")
                .empresa(pesJur)
                .build();

        var enderecoEnt = Endereco.builder()
                .cep("08995310")
                .bairro("Sao Leopoldo")
                .ruaLogradouro("Rua Francisco de Assis")
                .numero("357")
                .complemento("Casa")
                .cidade("Franco da Rocha")
                .estado("Sao Paulo")
                .uf("SP")
                .tipoEndereco(TipoEndereco.ENTREGA)
                .build();

        var enderecoCob = Endereco.builder()
                .cep("071258925")
                .bairro("Sao Mateus")
                .ruaLogradouro("Rua Junior Teodoro")
                .numero("200")
                .complemento("Casa")
                .cidade("Sao Paulo")
                .estado("Sao Paulo")
                .uf("SP")
                .tipoEndereco(TipoEndereco.COBRANCA)
                .build();

        var enderecos = new ArrayList<Endereco>();
        enderecos.add(enderecoEnt);
        enderecos.add(enderecoCob);

        pessoaFisica.setEnderecos(enderecos);

        return pessoaFisica;
    }

    public static PessoaJuridicaDTO genPessoaJuridica() {
        var pesJur = PessoaJuridica.builder()
                .cnpj("17.882.887/0001-91")
                .inscricaoEstadual("8520.3325.9996")
                .categoria("Eletronicos")
                .inscricaoMunicipal("22301.45698")
                .nomeFantasia("Ed.son")
                .razaoSocial("Ed.Son Eletronicos")
                .email("eds@gmail.com")
                .tipoPessoa("PJ")
                .nome("Eder Sonny")
                .telefone("92996874520")
                .build();

        var pessoaJuridicaDTO = PessoaJuridicaDTO.builder()
                .cnpj("60.736.280/0001-22")
                .inscricaoEstadual("7485.3210.3654")
                .categoria("Eletronicos")
                .inscricaoMunicipal("14785.36998")
                .nomeFantasia("Tadeu Macedo Eletronicos")
                .razaoSocial("TaMa Eletronicos")
                .email("tama@gmail.com")
                .tipoPessoa("PJ")
                .nome("Tadeu Macedo")
                .telefone("92996874520")
                .empresa(pesJur)
                .build();

        var enderecoCobEnt = Endereco.builder()
                .cep("07780000")
                .bairro("Sao Mateus")
                .ruaLogradouro("Rua Juliao Teodoro")
                .numero("58")
                .complemento("Comercial")
                .cidade("Sao Paulo")
                .estado("Sao Paulo")
                .uf("SP")
                .tipoEndereco(TipoEndereco.COBRANCA_ENTREGA)
                .build();

        var enderecos = new ArrayList<Endereco>();
        enderecos.add(enderecoCobEnt);

        pessoaJuridicaDTO.setEnderecos(enderecos);

        return pessoaJuridicaDTO;
    }

    public static SignUpRequest getSignUpRequest() {
        return SignUpRequest.builder()
                .username("aguirrej")
                .password("12345")
                .role("ROLE_BASIC")
                .build();
    }

    private static Date parseDate() {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse("02/03/1985");
        } catch (ParseException e) {
            return null;
        }
    }
}
