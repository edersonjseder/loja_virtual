package com.lojavirtual.service;

import com.lojavirtual.dto.ProdutoDto;
import com.lojavirtual.exception.CategoriaProdutoNaoEncontradoException;
import com.lojavirtual.exception.MarcaProdutoNaoEncontradoException;
import com.lojavirtual.exception.ProdutoException;
import com.lojavirtual.exception.ProdutoNaoEncontradoException;
import com.lojavirtual.model.MarcaProduto;
import com.lojavirtual.model.Produto;
import com.lojavirtual.repository.CategoriaProdutoRepository;
import com.lojavirtual.repository.MarcaProdutoRepository;
import com.lojavirtual.repository.ProdutoRepository;
import com.lojavirtual.utils.PessoaUtils;
import com.lojavirtual.utils.ProdutoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final MarcaProdutoRepository marcaProdutoRepository;
    private final CategoriaProdutoRepository categoriaProdutoRepository;
    private final ProdutoUtils produtoUtils;
    private final PessoaUtils pessoaUtils;

    public Page<ProdutoDto> buscarListaProdutos(Specification<Produto> spec, Pageable pageable) {
        return produtoUtils.toProdutoDtoPage(produtoRepository.findAll(spec, pageable));
    }

    public List<ProdutoDto> buscarProdutosPorDescricao(String descricao) {
        return produtoUtils.toProdutoDtoList(produtoRepository.findProdutoByDescricao(descricao));
    }

    public ProdutoDto buscarProdutoPorModelo(String modelo) {
        return produtoUtils.toProdutoDto(produtoRepository.findProdutoByModelo(modelo));
    }

    @Transactional
    public ProdutoDto salvarProduto(ProdutoDto produtoDto) {
        var produtoRegistered = new Produto();

        if (produtoDto.getId() == null) {
            if (produtoRepository.existeModeloProduto(produtoDto.getModelo())) {
                throw new ProdutoException("Já existe produto cadastrado com o modelo " + produtoDto.getModelo());
            }

            var produtoDaEmpresa = produtoRepository.findProdutoByModeloAndEmpresaId(produtoDto.getModelo(), produtoDto.getEmpresa().getId());

            if (produtoDaEmpresa != null) {
                throw new ProdutoException("Já existe produto cadastrado com o modelo " + produtoDto.getModelo());
            }

            var categoria = categoriaProdutoRepository.findCategoriaProdutoByNome(produtoDto.getCategoria())
                    .orElseThrow(() -> new CategoriaProdutoNaoEncontradoException(produtoDto.getCategoria()));

            var marca = marcaProdutoRepository.findMarcaProdutoByNome(produtoDto.getMarca())
                    .orElseThrow(() -> new MarcaProdutoNaoEncontradoException(produtoDto.getMarca()));

            var produto = new Produto();

            BeanUtils.copyProperties(produtoDto, produto);

            produto.setAtivo(Boolean.TRUE);
            produto.setMarcaProduto(marca);
            produto.setCategoriaProduto(categoria);
            produto.setEmpresa(pessoaUtils.toPessoaJuridica(produtoDto.getEmpresa()));

            produtoRegistered = produtoRepository.save(produto);

        } else {
            var produto = produtoRepository.findById(produtoDto.getId())
                    .orElseThrow(() -> new ProdutoNaoEncontradoException(produtoDto.getId()));

            produtoUtils.setProduto(produtoDto, produto);

            produto.setEmpresa(pessoaUtils.toPessoaJuridica(produtoDto.getEmpresa()));

            produtoRegistered = produto;

        }

        return produtoUtils.toProdutoDto(produtoRegistered);
    }

    public void removerProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
