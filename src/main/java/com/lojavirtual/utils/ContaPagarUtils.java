package com.lojavirtual.utils;

import com.lojavirtual.dto.ContaPagarDto;
import com.lojavirtual.model.ContaPagar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContaPagarUtils {
    private final PessoaUtils pessoaUtils;
    private final DateUtils dateUtils;

    public ContaPagar setContaPagar(ContaPagar contaPagar, ContaPagarDto contaPagarDto) throws ParseException {

        contaPagar.setId(contaPagarDto.getId());
        contaPagar.setValorTotal(contaPagarDto.getValorTotal());
        contaPagar.setDataVencimento(dateUtils.parseStringToDate(contaPagarDto.getDataVencimento()));
        contaPagar.setDataPagamento(dateUtils.getCurrentDate());
        contaPagar.setDescricao(contaPagarDto.getDescricao());
        contaPagar.setStatusContaPagar(contaPagarDto.getStatus());

        return contaPagar;
    }

    public ContaPagar toContaPagar(ContaPagarDto contaPagarDto) throws ParseException {
        return ContaPagar.builder()
                .id(contaPagarDto.getId())
                .valorTotal(contaPagarDto.getValorTotal())
                .dataVencimento(dateUtils.parseStringToDate(contaPagarDto.getDataVencimento()))
                .dataPagamento(dateUtils.getCurrentDate())
                .descricao(contaPagarDto.getDescricao())
                .statusContaPagar(contaPagarDto.getStatus())
                .empresa(pessoaUtils.toPessoaJuridica(contaPagarDto.getEmpresa()))
                .pessoaFornecedor(pessoaUtils.toPessoaJuridica(contaPagarDto.getFornecedor()))
                .pessoa(pessoaUtils.toPessoaFisica(contaPagarDto.getPessoa()))
                .build();
    }

    public ContaPagarDto toContaPagarDto(ContaPagar contaPagar) throws ParseException {
        return ContaPagarDto.builder()
                .id(contaPagar.getId())
                .valorTotal(contaPagar.getValorTotal())
                .dataVencimento(dateUtils.parseDate(contaPagar.getDataVencimento()))
                .dataPagamento(dateUtils.parseDate(contaPagar.getDataPagamento()))
                .descricao(contaPagar.getDescricao())
                .status(contaPagar.getStatusContaPagar())
                .empresa(pessoaUtils.toPessoaJuridicaDto(contaPagar.getEmpresa()))
                .fornecedor(pessoaUtils.toPessoaJuridicaDto(contaPagar.getPessoaFornecedor()))
                .pessoa(pessoaUtils.toPessoaFisicaDto(contaPagar.getPessoa()))
                .build();
    }

    public List<ContaPagarDto> toContaPagarDtoList(List<ContaPagar> contaPagarLst) throws ParseException {
        return contaPagarLst.stream().map(contaPagar -> ContaPagarDto.builder()
                .id(contaPagar.getId())
                .valorTotal(contaPagar.getValorTotal())
                .dataVencimento(dateUtils.parseDate(contaPagar.getDataVencimento()))
                .dataPagamento(dateUtils.parseDate(contaPagar.getDataPagamento()))
                .descricao(contaPagar.getDescricao())
                .status(contaPagar.getStatusContaPagar())
                .empresa(pessoaUtils.toPessoaJuridicaDto(contaPagar.getEmpresa()))
                .fornecedor(pessoaUtils.toPessoaJuridicaDto(contaPagar.getPessoaFornecedor()))
                .pessoa(pessoaUtils.toPessoaFisicaDto(contaPagar.getPessoa()))
                .build()).collect(Collectors.toList());
    }
}
