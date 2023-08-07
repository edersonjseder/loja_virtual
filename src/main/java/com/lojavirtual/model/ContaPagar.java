package com.lojavirtual.model;

import com.lojavirtual.enums.StatusContaPagar;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "conta_pagar")
@SequenceGenerator(name = "seq_conta_pagar", sequenceName = "seq_conta_pagar", allocationSize = 1)
public class ContaPagar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_pagar")
    private Long id;
    @Column(nullable = false)
    private BigDecimal valorTotal;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusContaPagar statusContaPagar;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
    private Pessoa pessoa;
    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_fornecedor_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fornecedor_fk"))
    private Pessoa pessoaFornecedor;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
    private Pessoa empresa;
    @OneToOne(mappedBy = "contaPagar")
    private NotaFiscalCompra notaFiscalCompra;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        ContaPagar endereco = (ContaPagar) o;
        if (id == null) {
            return endereco.id == null;
        } else return id.equals(endereco.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
