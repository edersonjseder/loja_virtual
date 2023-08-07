package com.lojavirtual.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nota_fiscal_venda")
@SequenceGenerator(name = "seq_nota_fiscal_venda", sequenceName = "seq_nota_fiscal_venda", allocationSize = 1)
public class NotaFiscalVenda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_fiscal_venda")
    private Long id;
    @Column(nullable = false)
    private String numero;
    @Column(nullable = false)
    private String serie;
    @Column(nullable = false)
    private String tipo;
    @Column(columnDefinition = "text", nullable = false)
    private String xml;
    @Column(columnDefinition = "text", nullable = false)
    private String pdf;
    @OneToOne
    @JoinColumn(name = "venda_compra_loja_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "venda_compra_loja_fk"))
    private VendaCompraLoja vendaCompraLoja;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
    private Pessoa empresa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        NotaFiscalVenda notaFiscalVenda = (NotaFiscalVenda) o;
        if (id == null) {
            return notaFiscalVenda.id == null;
        } else return id.equals(notaFiscalVenda.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
