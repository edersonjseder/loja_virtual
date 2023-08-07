package com.lojavirtual.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lojavirtual.enums.TipoEndereco;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "endereco")
@SequenceGenerator(name = "seq_endereco", sequenceName = "seq_endereco", allocationSize = 1)
public class Endereco implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco")
    private Long id;
    @Column(nullable = false)
    private String uf;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String bairro;
    private String complemento;
    @Column(nullable = false)
    private String numero;
    @Column(nullable = false)
    private String ruaLogradouro;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoEndereco tipoEndereco;

    @JsonIgnore
    @ManyToOne(targetEntity = Pessoa.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pessoa_id", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
    private Pessoa pessoa;

    @JsonIgnore
    @ManyToOne(targetEntity = Pessoa.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
    private Pessoa empresa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
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
