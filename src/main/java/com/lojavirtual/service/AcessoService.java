package com.lojavirtual.service;

import com.lojavirtual.exception.AcessoNaoEncontradoException;
import com.lojavirtual.model.Acesso;
import com.lojavirtual.repository.AcessoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcessoService {
    private final AcessoRepository acessoRepository;

    public Acesso salvarAcesso(Acesso acesso) {
        return acessoRepository.save(acesso);
    }

    public void removerAcesso(Long id) {
        acessoRepository.deleteById(id);
    }

    public List<Acesso> carregarAcessos() {
        return acessoRepository.findAll();
    }

   public List<Acesso> carregarAcessosPorDescricao(String desc) {
        return acessoRepository.carregarAcessosPorDescricao(desc)
                .orElseThrow(() -> new AcessoNaoEncontradoException(desc));
    }

    public Acesso carregarAcessoPorId(Long id) {
        return acessoRepository.findById(id)
                .orElseThrow(() -> new AcessoNaoEncontradoException(id));
    }
}
