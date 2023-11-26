package com.lojavirtual.service;

import com.lojavirtual.dto.PessoaFisicaDTO;
import com.lojavirtual.dto.PessoaJuridicaDTO;
import com.lojavirtual.dto.PessoaResponseDto;
import com.lojavirtual.enums.TipoPessoa;
import com.lojavirtual.exception.PessoaException;
import com.lojavirtual.repository.PessoaRepository;
import com.lojavirtual.utils.PessoaUtils;
import com.lojavirtual.utils.ValidaCNPJ;
import com.lojavirtual.utils.ValidaCPF;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lojavirtual.constants.PessoaMensagemConstants.*;

@Service
@RequiredArgsConstructor
public class PessoaUserService {
    private final PessoaRepository pessoaRepository;
    private final UsuarioService usuarioService;
    private final PessoaUtils pessoaUtils;
    private final EstadoService estadoService;
    private final CepService cepService;
    private final ContagemAcessoApiService contagemAcessoApiService;

    /**
     * Search Pessoa Fisica by name
     * @param nome parameter from API URL
     * @return the list of Pessoa Fisica
     */
    public List<PessoaFisicaDTO> buscarPessoaFisicaPorNome(String nome) {
        contagemAcessoApiService.atualizaAcessoEndPoint();
        return pessoaUtils.toListPessoaFisicaDto(pessoaRepository.buscarPorNomePf(nome));
    }

    /**
     * Search Pessoa Fisica by CPF
     * @param cpf parameter from API URL
     * @return the Pessoa Fisica object
     */
    public PessoaFisicaDTO buscarPessoaFisicaPorCpf(String cpf) {
        return pessoaUtils.toPessoaFisicaDto(pessoaRepository.verificarCpfCadastrado(cpf));
    }

    /**
     * Stores Pessoa Fisica to Database
     * @param pessoaFisicaDTO coming from API URL
     * @return The response with PF name and email and user credentials
     */
    @Transactional
    public PessoaResponseDto guardarPessoaFisica(PessoaFisicaDTO pessoaFisicaDTO) {
        if (!ValidaCPF.isCPF(pessoaFisicaDTO.getCpf())) {
            throw new PessoaException(CPF_MENSAGEM + pessoaFisicaDTO.getCpf());
        }

        if (pessoaFisicaDTO.getId() == null && pessoaRepository.verificarCpfCadastrado(pessoaFisicaDTO.getCpf()) != null) {
            throw new PessoaException(CPF_EXISTENTE_MENSAGEM + pessoaFisicaDTO.getCpf());
        }

        if (pessoaFisicaDTO.getTipoPessoa() == null) {
            pessoaFisicaDTO.setTipoPessoa(TipoPessoa.FISICA.name());
        }

        if (pessoaFisicaDTO.getId() == null) {
            pessoaFisicaDTO.getEnderecos().forEach(endereco -> {
                var cep = cepService.consultaCep(endereco.getCep());
                var estado = estadoService.buscarEstadoPorUf(cep.getUf());

                endereco.setRuaLogradouro(cep.getLogradouro());
                endereco.setBairro(cep.getBairro());
                endereco.setCidade(cep.getLocalidade());
                endereco.setEstado(estado.getNome());
                endereco.setUf(cep.getUf());
            });
        }

        var storedPessoaFisica = pessoaRepository.save(pessoaUtils.toPessoaFisica(pessoaFisicaDTO));

        pessoaFisicaDTO.getEnderecos().forEach(end -> {
            end.setPessoa(storedPessoaFisica);
            end.setEmpresa(storedPessoaFisica.getEmpresa());
        });

        storedPessoaFisica.setEnderecos(pessoaFisicaDTO.getEnderecos());

        var token = (pessoaFisicaDTO.getId() == null ? usuarioService.registrarUsuario(storedPessoaFisica) : null);

        return pessoaUtils.toPessoaResponseDto(storedPessoaFisica, token);
    }

    /**
     * Search Pessoa Juridica by name
     * @param nome parameter from API URL
     * @return the list of Pessoa Juridica
     */
    public List<PessoaJuridicaDTO> buscarPessoaJuridicaPorNome(String nome) {
        return pessoaUtils.toListPessoaJuridicaDto(pessoaRepository.buscarPorNomePj(nome));
    }

    /**
     * Search Pessoa Juridica by CNPJ
     * @param cnpj parameter from API URL
     * @return the Pessoa Juridica object
     */
    public PessoaJuridicaDTO buscarPessoaJuridicaPorCnpj(String cnpj) {
        return pessoaUtils.toPessoaJuridicaDto(pessoaRepository.verificarCnpjCadastrado(cnpj));
    }

    /**
     * Stores Pessoa Juridica to Database
     * @param pessoaJuridicaDTO coming from API URL
     * @return The response with PJ name and email and user credentials
     */
    @Transactional
    public PessoaResponseDto guardarPessoaJuridica(PessoaJuridicaDTO pessoaJuridicaDTO) {

        if (!ValidaCNPJ.isCNPJ(pessoaJuridicaDTO.getCnpj())) {
            throw new PessoaException(CNPJ_MENSAGEM + pessoaJuridicaDTO.getCnpj());
        }

        if (pessoaJuridicaDTO.getTipoPessoa() == null) {
            throw new PessoaException(TIPO_PESSOA_MENSAGEM);
        }

        if (pessoaJuridicaDTO.getId() == null && pessoaRepository.verificarCnpjCadastrado(pessoaJuridicaDTO.getCnpj()) != null) {
            throw new PessoaException(CNPJ_EXISTENTE_MENSAGEM + pessoaJuridicaDTO.getCnpj());
        }

        if (pessoaJuridicaDTO.getId() == null && pessoaRepository.verificarInsEstadualCadastrado(pessoaJuridicaDTO.getInscricaoEstadual()) != null) {
            throw new PessoaException(IE_EXISTENTE_MENSAGEM + pessoaJuridicaDTO.getInscricaoEstadual());
        }

        if (pessoaJuridicaDTO.getId() == null && pessoaRepository.verificarInsMunicipalCadastrado(pessoaJuridicaDTO.getInscricaoMunicipal()) != null) {
            throw new PessoaException(IM_EXISTENTE_MENSAGEM + pessoaJuridicaDTO.getInscricaoMunicipal());
        }

        if (pessoaJuridicaDTO.getId() == null) {
            pessoaJuridicaDTO.getEnderecos().forEach(endereco -> {
                var cep = cepService.consultaCep(endereco.getCep());
                var estado = estadoService.buscarEstadoPorUf(endereco.getUf());

                endereco.setRuaLogradouro(cep.getLogradouro());
                endereco.setBairro(cep.getBairro());
                endereco.setCidade(cep.getLocalidade());
                endereco.setEstado(estado.getNome());
                endereco.setUf(cep.getUf());
            });
        }

        var storedPessoaJuridica = pessoaRepository.save(pessoaUtils.toPessoaJuridica(pessoaJuridicaDTO));

        pessoaJuridicaDTO.getEnderecos().forEach(end -> {
            end.setEmpresa(storedPessoaJuridica.getEmpresa());
        });

        storedPessoaJuridica.setEnderecos(pessoaJuridicaDTO.getEnderecos());

        var token = (pessoaJuridicaDTO.getId() == null ? usuarioService.registrarUsuario(storedPessoaJuridica) : null);

        return pessoaUtils.toPessoaResponseDto(storedPessoaJuridica, token);
    }
}
