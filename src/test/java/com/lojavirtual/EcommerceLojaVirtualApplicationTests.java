package com.lojavirtual;

import com.lojavirtual.controller.AcessoController;
import com.lojavirtual.model.Acesso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EcommerceLojaVirtualApplication.class)
class EcommerceLojaVirtualApplicationTests {

	@Autowired
	private AcessoController acessoController;

	@Test
	public void cadastraAcessoTest() {
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_BASIC");
		Assertions.assertNull(acesso.getId());
		acesso = acessoController.salvarAcesso(acesso).getBody();
		Assertions.assertTrue(acesso.getId() > 0);
	}

	@Test
	public void carregarAcessoTest() {
		var acessos = acessoController.carregarAcessos();
		Assertions.assertNotNull(acessos);
		Assertions.assertEquals(4, acessos.getBody().size());
	}

}
