package com.lojavirtual.service;

import com.lojavirtual.model.Acesso;
import com.lojavirtual.repository.AcessoRepository;
import org.springframework.stereotype.Service;

@Service
public class AcessoService {
    private final AcessoRepository acessoRepository;

    public AcessoService(AcessoRepository acessoRepository) {
        this.acessoRepository = acessoRepository;
    }

    public Acesso salvarAcesso(Acesso acesso) {
        return acessoRepository.save(acesso);
    }
}
