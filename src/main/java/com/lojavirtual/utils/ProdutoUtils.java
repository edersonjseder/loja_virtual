package com.lojavirtual.utils;

import com.lojavirtual.dto.ProdutoDto;
import com.lojavirtual.model.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProdutoUtils {
    private final PessoaUtils pessoaUtils;
    private final CategoriaUtils categoriaUtils;
    private final MarcaUtils marcaUtils;

    public Produto setProduto(ProdutoDto produtoDto, Produto produto) {

        produto.setId(produtoDto.getId());
        produto.setNome(produtoDto.getNome());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setAltura(produtoDto.getAltura());
        produto.setLargura(produtoDto.getLargura());
        produto.setPeso(produtoDto.getPeso());
        produto.setProfundidade(produtoDto.getProfundidade());
        produto.setTipoUnidade(produtoDto.getTipoUnidade());
        produto.setModelo(produtoDto.getModelo());
        produto.setQtdeEstoque(produtoDto.getQtdeEstoque());
        produto.setValorVenda(produtoDto.getValorVenda());
        produto.setAtivo(produtoDto.getAtivo());

        return produto;
    }

    public ProdutoDto toProdutoDto(Produto produto) {
        return ProdutoDto.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .altura(produto.getAltura())
                .largura(produto.getLargura())
                .peso(produto.getPeso())
                .profundidade(produto.getProfundidade())
                .tipoUnidade(produto.getTipoUnidade())
                .modelo(produto.getModelo())
                .qtdeEstoque(produto.getQtdeEstoque())
                .valorVenda(produto.getValorVenda())
                .ativo(produto.getAtivo())
                .empresa(pessoaUtils.toPessoaJuridicaDto(produto.getEmpresa()))
                .marcaProduto(marcaUtils.toMarcaProdutoDto(produto.getMarcaProduto()))
                .categoriaProduto(categoriaUtils.toCategoriaProdutoDto(produto.getCategoriaProduto()))
                .build();
    }

    public List<ProdutoDto> toProdutoDtoList(List<Produto> produtos) {
        return produtos.stream().map(produto -> ProdutoDto.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .altura(produto.getAltura())
                .largura(produto.getLargura())
                .peso(produto.getPeso())
                .profundidade(produto.getProfundidade())
                .tipoUnidade(produto.getTipoUnidade())
                .modelo(produto.getModelo())
                .qtdeEstoque(produto.getQtdeEstoque())
                .valorVenda(produto.getValorVenda())
                .empresa(pessoaUtils.toPessoaJuridicaDto(produto.getEmpresa()))
                .marcaProduto(marcaUtils.toMarcaProdutoDto(produto.getMarcaProduto()))
                .categoriaProduto(categoriaUtils.toCategoriaProdutoDto(produto.getCategoriaProduto()))
                .build()).collect(Collectors.toList());
    }

    public Page<ProdutoDto> toProdutoDtoPage(Page<Produto> produtos) {
        return produtos.map(produto -> ProdutoDto.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .altura(produto.getAltura())
                .largura(produto.getLargura())
                .peso(produto.getPeso())
                .profundidade(produto.getProfundidade())
                .tipoUnidade(produto.getTipoUnidade())
                .modelo(produto.getModelo())
                .qtdeEstoque(produto.getQtdeEstoque())
                .valorVenda(produto.getValorVenda())
                .empresa(pessoaUtils.toPessoaJuridicaDto(produto.getEmpresa()))
                .marcaProduto(marcaUtils.toMarcaProdutoDto(produto.getMarcaProduto()))
                .categoriaProduto(categoriaUtils.toCategoriaProdutoDto(produto.getCategoriaProduto()))
                .build());
    }
}
