package com.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutoDto {

    public interface ProdutoView {
        interface ProdutoPost {}
        interface ProdutoPut {}
        interface ImagePut {}
    }

    private Long id;

    @NotBlank(groups = {ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class}, message = "Nome do produto deve ser informado")
    @Size(min = 6, groups = {ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class}, message = "Nome do produto deve ter mais de 6 letras")
    @JsonView({ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class})
    private String nome;

    @NotBlank(groups = {ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class}, message = "Modelo do produto deve ser informado")
    @JsonView({ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class})
    private String modelo;

    private String linkYoutube;
    private Integer qtdeClique;

    @NotNull(groups = {ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class}, message = "Valor do produto deve ser informado")
    @JsonView({ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class})
    private BigDecimal valorVenda;

    @NotNull(groups = {ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class}, message = "Peso do produto deve ser informado")
    @JsonView({ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class})
    private Double peso;

    @NotNull(groups = {ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class}, message = "Largura do produto deve ser informada")
    @JsonView({ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class})
    private Double largura;

    @NotNull(groups = {ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class}, message = "Altura do produto deve ser informada")
    @JsonView({ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class})
    private Double altura;

    @NotNull(groups = {ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class}, message = "Profundidade do produto deve ser informada")
    @JsonView({ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class})
    private Double profundidade;

    private Boolean alertaQtdeEstoque;
    private Integer qtdeAlerta;

    @NotNull(groups = {ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class}, message = "Quantidade do produto deve ser informada")
    @JsonView({ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class})
    private Integer qtdeEstoque;

    @NotBlank(groups = {ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class}, message = "Descricao do produto deve ser informada")
    @JsonView({ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class})
    private String descricao;

    @NotBlank(groups = {ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class}, message = "Tipo de Unidade do produto deve ser informada")
    @JsonView({ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class})
    private String tipoUnidade;

    @NotNull(groups = {ProdutoView.ProdutoPut.class}, message = "Status deve ser informado")
    @JsonView(ProdutoView.ProdutoPut.class)
    private Boolean ativo;

    @NotBlank(groups = ProdutoView.ImagePut.class, message = "URL da Imagem deve ser informada")
    @JsonView(ProdutoView.ImagePut.class)
    private String imageUrl;

    @NotNull(groups = ProdutoView.ProdutoPost.class, message = "Empresa responsavel deve ser informada")
    @JsonView(ProdutoView.ProdutoPost.class)
    private PessoaJuridicaDTO empresa;

    @NotNull(groups = ProdutoView.ProdutoPost.class, message = "Categoria do produto deve ser informada")
    @JsonView(ProdutoView.ProdutoPost.class)
    private String categoria;

    @NotBlank(groups = {ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class}, message = "Marca do produto deve ser informada")
    @JsonView({ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class})
    private String marca;

    private CategoriaProdutoDto categoriaProduto;

    private MarcaProdutoDto marcaProduto;
}
