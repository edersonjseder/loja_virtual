package com.lojavirtual.service;

import com.lojavirtual.dto.ContaPagarDto;
import com.lojavirtual.exception.ContaPagarException;
import com.lojavirtual.exception.ContaPagarNaoEncontradaException;
import com.lojavirtual.exception.PessoaNaoEncontradaException;
import com.lojavirtual.model.ContaPagar;
import com.lojavirtual.repository.ContaPagarRepository;
import com.lojavirtual.repository.PessoaRepository;
import com.lojavirtual.utils.ContaPagarUtils;
import com.lojavirtual.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

import static com.lojavirtual.constants.ContaPagarConstants.*;

@Service
@RequiredArgsConstructor
public class ContaPagarService {
    private final ContaPagarRepository contaPagarRepository;
    private final PessoaRepository pessoaRepository;
    private final ContaPagarUtils contaPagarUtils;
    private final DateUtils dateUtils;

    public List<ContaPagarDto> buscarContaPagarLista() {
        try {
            return contaPagarUtils.toContaPagarDtoList(contaPagarRepository.findAll());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerContaPagar(Long id) {
        contaPagarRepository.deleteById(id);
    }

    public ContaPagar buscarContaPorId(Long id) {
        return contaPagarRepository.findById(id)
                .orElseThrow(() -> new ContaPagarNaoEncontradaException(id));
    }

    public List<ContaPagar> buscarContaPorDescricao(String descricao) {
        return contaPagarRepository.buscarContaPagarPorDescricao(descricao);
    }

    @Transactional
    public ContaPagarDto salvarContaPagar(ContaPagarDto contaPagarDto) {
        var contasPagar = new ContaPagar();
        try {
            if (contaPagarDto.getId() == null) {
                var empresa = pessoaRepository.verificarCnpjCadastrado(contaPagarDto.getEmpresaCnpj());

                if (empresa == null) {
                    throw new PessoaNaoEncontradaException(EMPRESA_NAO_ENCONTRADA_MESSAGE);
                }

                var fornecedor = pessoaRepository.verificarCnpjCadastrado(contaPagarDto.getFornecedorCnpj());

                if (fornecedor == null) {
                    throw new PessoaNaoEncontradaException(FORNECEDOR_NAO_ENCONTRADO_MESSAGE);
                }

                var pessoa = pessoaRepository.verificarCpfCadastrado(contaPagarDto.getPessoaCpf());

                if (pessoa == null) {
                    throw new PessoaNaoEncontradaException(PESSOA_NAO_ENCONTRADA_MESSAGE);
                }

                var contas = contaPagarRepository.buscarContaPagarPorDescricao(contaPagarDto.getDescricao());

                if (!contas.isEmpty()) {
                    throw new ContaPagarException(CONTA_PAGAR_EXISTENTE_MESSAGE);
                }

                var conta = new ContaPagar();

                BeanUtils.copyProperties(contaPagarDto, conta);

                conta.setDataVencimento(dateUtils.parseStringToDate(contaPagarDto.getDataVencimento()));
                conta.setDataPagamento(dateUtils.getCurrentDate());
                conta.setStatusContaPagar(contaPagarDto.getStatus());
                conta.setPessoaFornecedor(fornecedor);
                conta.setEmpresa(empresa);
                conta.setPessoa(pessoa);

                contasPagar = contaPagarRepository.save(conta);

            } else {
                contasPagar = contaPagarRepository.findById(contaPagarDto.getId())
                        .orElseThrow(() -> new ContaPagarException(CONTA_PAGAR_NAO_ENCONTRADO_MESSAGE));

                contasPagar = contaPagarUtils.setContaPagar(contasPagar, contaPagarDto);

            }

            return contaPagarUtils.toContaPagarDto(contasPagar);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
