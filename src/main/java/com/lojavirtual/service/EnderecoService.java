package com.lojavirtual.service;

import com.lojavirtual.model.Endereco;
import com.lojavirtual.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public Endereco salvarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> salvarEnderecos(List<Endereco> enderecos) {
        return enderecoRepository.saveAll(enderecos);
    }
}
