package com.lojavirtual.service;

import com.lojavirtual.dto.MarcaProdutoDto;
import com.lojavirtual.exception.MarcaProdutoException;
import com.lojavirtual.exception.MarcaProdutoNaoEncontradoException;
import com.lojavirtual.repository.MarcaProdutoRepository;
import com.lojavirtual.utils.MarcaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaProdutoService {
    private final MarcaUtils marcaUtils;
    private final MarcaProdutoRepository marcaProdutoRepository;

    public List<MarcaProdutoDto> buscarListaMarcaProduto() {
        return marcaUtils.toListaMarcaProdutoDto(marcaProdutoRepository.findAll());
    }

    public List<MarcaProdutoDto> buscarMarcaProdutoPorDescricao(String descricao) {
        return marcaUtils.toListaMarcaProdutoDto(marcaProdutoRepository.buscarMarcaProdutoPorDescricao(descricao));
    }

    public MarcaProdutoDto buscarMarcaProdutoPorNome(String nome) {
        return marcaUtils
                .toMarcaProdutoDto(marcaProdutoRepository.findMarcaProdutoByNome(nome)
                .orElseThrow(() -> new MarcaProdutoNaoEncontradoException(nome)));
    }

    public MarcaProdutoDto guardarMarcaProduto(MarcaProdutoDto marcaProdutoDto) {
        if (marcaProdutoDto.getEmpresa() == null) {
            throw new MarcaProdutoException("A Empresa deve ser Informada.");
        }

        if (marcaProdutoDto.getId() == null && marcaProdutoRepository.existeMarca(marcaProdutoDto.getNome())) {
            throw new MarcaProdutoException("Nao pode cadastrar marca com o mesmo nome.");
        }

        return marcaUtils
                .toMarcaProdutoDto(marcaProdutoRepository
                        .save(marcaUtils.toMarcaProduto(marcaProdutoDto)));
    }

    public void removerMarcaProduto(Long id) {
        marcaProdutoRepository.deleteById(id);
    }
}
