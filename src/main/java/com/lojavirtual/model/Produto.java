package com.lojavirtual.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produto")
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1)
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
    private Long id;
    @Column(nullable = false)
    private String nome;
    private String linkYoutube;
    private Integer qtdeClique = 0;
    @Column(nullable = false)
    private BigDecimal valorVenda = BigDecimal.ZERO;
    @Column(nullable = false)
    private Double peso;
    @Column(nullable = false)
    private Double largura;
    @Column(nullable = false)
    private Double altura;
    @Column(nullable = false)
    private Double profundidade;
    private Boolean alertaQtdeEstoque = Boolean.FALSE;
    private Integer qtdeAlerta = 0;
    @Column(nullable = false)
    private Integer qtdeEstoque = 0;
    @Column(columnDefinition = "text", length = 2000, nullable = false)
    private String descricao;
    @Column(nullable = false)
    private String tipoUnidade;
    @Column(nullable = false)
    private Boolean ativo = Boolean.TRUE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        if (id == null) {
            return produto.id == null;
        } else return id.equals(produto.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
