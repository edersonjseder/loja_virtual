package com.lojavirtual.specifications;

import com.lojavirtual.model.Produto;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path="nome", spec= Equal.class),
        @Spec(path="modelo", spec=Equal.class),
        @Spec(path="descricao", spec= Like.class)
})
public interface ProdutoSpec extends Specification<Produto> {
}
