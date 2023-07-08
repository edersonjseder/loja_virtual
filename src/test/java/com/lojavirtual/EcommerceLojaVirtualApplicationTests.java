package com.lojavirtual;

import com.lojavirtual.model.Acesso;
import com.lojavirtual.repository.AcessoRepository;
import com.lojavirtual.service.AcessoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EcommerceLojaVirtualApplication.class)
class EcommerceLojaVirtualApplicationTests {

	@Autowired
	private AcessoService acessoService;

	@Test
	public void testCadastraAcesso() {
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_BASIC");
		acessoService.salvarAcesso(acesso);
	}

}
