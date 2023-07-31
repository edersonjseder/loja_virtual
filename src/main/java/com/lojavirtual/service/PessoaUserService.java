package com.lojavirtual.service;

import com.lojavirtual.dto.PessoaFisicaDTO;
import com.lojavirtual.dto.PessoaJuridicaDTO;
import com.lojavirtual.dto.PessoaResponseDto;
import com.lojavirtual.exception.PessoaException;
import com.lojavirtual.repository.PessoaRepository;
import com.lojavirtual.utils.PessoaUtils;
import com.lojavirtual.utils.ValidaCNPJ;
import com.lojavirtual.utils.ValidaCPF;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.lojavirtual.enums.PessoaMensagemEnum.*;

@Service
@RequiredArgsConstructor
public class PessoaUserService {
    private final PessoaRepository pessoaRepository;
    private final EnderecoService enderecoService;
    private final UsuarioService usuarioService;
    private final PessoaUtils pessoaUtils;

    @Transactional
    public PessoaResponseDto cadastrarPessoaJuridica(PessoaJuridicaDTO pessoaJuridicaDTO) {
        if (!ValidaCNPJ.isCNPJ(pessoaJuridicaDTO.getCnpj())) {
            throw new PessoaException(CNPJ_MENSAGEM + pessoaJuridicaDTO.getCnpj());
        }

        if (pessoaRepository.verificarCnpjCadastrado(pessoaJuridicaDTO.getCnpj()) != null) {
            throw new PessoaException(CNPJ_EXISTENTE_MENSAGEM + pessoaJuridicaDTO.getCnpj());
        }

        if (pessoaRepository.verificarInsEstadualCadastrado(pessoaJuridicaDTO.getInscricaoEstadual()) != null) {
            throw new PessoaException(IE_EXISTENTE_MENSAGEM + pessoaJuridicaDTO.getInscricaoEstadual());
        }

        if (pessoaRepository.verificarInsMunicipalCadastrado(pessoaJuridicaDTO.getInscricaoMunicipal()) != null) {
            throw new PessoaException(IM_EXISTENTE_MENSAGEM + pessoaJuridicaDTO.getInscricaoMunicipal());
        }

        var storedPessoaJuridica = pessoaRepository.save(pessoaUtils.fromPessoaJuridicaDto(pessoaJuridicaDTO));

        pessoaJuridicaDTO.getEnderecos().forEach(end -> end.setPessoa(storedPessoaJuridica));

        var storedList = enderecoService.salvarEnderecos(pessoaJuridicaDTO.getEnderecos());

        storedPessoaJuridica.setEnderecos(storedList);

        var token = usuarioService.registrarUsuario(storedPessoaJuridica);

        return pessoaUtils.toPessoaResponsePjDto(storedPessoaJuridica, token);
    }

    @Transactional
    public PessoaResponseDto cadastrarPessoaFisica(PessoaFisicaDTO pessoaFisicaDTO) {
        if (!ValidaCPF.isCPF(pessoaFisicaDTO.getCpf())) {
            throw new PessoaException(CPF_MENSAGEM + pessoaFisicaDTO.getCpf());
        }

        if (pessoaRepository.verificarCpfCadastrado(pessoaFisicaDTO.getCpf()) != null) {
            throw new PessoaException(CPF_EXISTENTE_MENSAGEM + pessoaFisicaDTO.getCpf());
        }

        var storedPessoaFisica = pessoaRepository.save(pessoaUtils.fromPessoaFisicaDto(pessoaFisicaDTO));

        pessoaFisicaDTO.getEnderecos().forEach(end -> end.setPessoa(storedPessoaFisica));

        var storedList = enderecoService.salvarEnderecos(pessoaFisicaDTO.getEnderecos());

        storedPessoaFisica.setEnderecos(storedList);

        var token = usuarioService.registrarUsuario(storedPessoaFisica);

        return pessoaUtils.toPessoaResponsePfDto(storedPessoaFisica, token);
    }
}
