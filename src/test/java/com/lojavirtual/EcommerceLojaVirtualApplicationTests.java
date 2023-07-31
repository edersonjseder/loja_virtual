package com.lojavirtual;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lojavirtual.response.TokenResponse;
import com.lojavirtual.utils.ToJsonConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = EcommerceLojaVirtualApplication.class)
class EcommerceLojaVirtualApplicationTests {

    @Autowired
    private ToJsonConverter toJsonConverter;

    @Test
	public void jsonConvertTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        var message = toJsonConverter.toJson("Mensagem de texto");

        TokenResponse tokenResponse = mapper.readValue(message, TokenResponse.class);
        assertEquals("Mensagem de texto", tokenResponse.getAuthorization());
    }

//	@Autowired
//	private AcessoController acessoController;
//	@Autowired
//	private AcessoRepository acessoRepository;
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//
//	@Test
//	public void restApiCadastroAcessoTest() throws Exception {
//		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webApplicationContext);
//		MockMvc mockMvc = builder.build();
//
//		Acesso acesso = new Acesso();
//		acesso.setDescricao("ROLE_COMPRADOR");
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//
//		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.post("/salvarAcesso")
//				.content(mapper.writeValueAsString(acesso))
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON));
//
//		System.out.println(retornoApi.andReturn().getResponse().getContentAsString());
//
//		Acesso retornoFromApi = mapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);
//
//		assertEquals(acesso.getDescricao(), retornoFromApi.getDescricao());
//	}
//
//	@Test
//	public void restApiRemoverAcessoTest() throws Exception {
//		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webApplicationContext);
//		MockMvc mockMvc = builder.build();
//
//		Acesso acesso = new Acesso();
//		acesso.setDescricao("ROLE_COMPRADOR");
//
//		acesso = acessoRepository.save(acesso);
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//
//		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.delete("/removerAcesso")
//				.content(mapper.writeValueAsString(acesso))
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON));
//
//		System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
//		System.out.println("Status de Retorno: " + retornoApi.andReturn().getResponse().getStatus());
//
//		assertEquals("Acesso Removido.", retornoApi.andReturn().getResponse().getContentAsString());
//		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
//	}
//
//	@Test
//	public void restApiRemoverAcessoPorIdTest() throws Exception {
//		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webApplicationContext);
//		MockMvc mockMvc = builder.build();
//
//		Acesso acesso = new Acesso();
//		acesso.setDescricao("ROLE_COMPRADOR");
//
//		acesso = acessoRepository.save(acesso);
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//
//		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.delete("/removerAcessoPorId/" + acesso.getId())
//				.content(mapper.writeValueAsString(acesso))
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON));
//
//		System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
//		System.out.println("Status de Retorno: " + retornoApi.andReturn().getResponse().getStatus());
//
//		assertEquals("Acesso Removido.", retornoApi.andReturn().getResponse().getContentAsString());
//		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
//	}
//
//	@Test
//	public void restApiCarregarAcessoPorIdTest() throws Exception {
//		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webApplicationContext);
//		MockMvc mockMvc = builder.build();
//
//		Acesso acesso = new Acesso();
//		acesso.setDescricao("ROLE_OBTER_ID");
//
//		acesso = acessoRepository.save(acesso);
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//
//		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.get("/carregarAcessoPorId/" + acesso.getId())
//				.content(mapper.writeValueAsString(acesso))
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON));
//
//		System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
//
//		Acesso retornoFromApi = mapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);
//
//		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
//
//		assertEquals(acesso.getDescricao(), retornoFromApi.getDescricao());
//	}
//
//	@Test
//	public void restApiCarregarAcessosPorDescricaoTest() throws Exception {
//		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webApplicationContext);
//		MockMvc mockMvc = builder.build();
//
//		Acesso acesso = new Acesso();
//		acesso.setDescricao("ROLE_OBTER_LIST");
//
//		acesso = acessoRepository.save(acesso);
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//
//		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.get("/carregarAcessosPorDescricao/LIST")
//				.content(mapper.writeValueAsString(acesso))
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON));
//
//		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
//
//		System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
//
//		var retornoApiList = mapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), new TypeReference<List<Acesso>>() {});
//
//		assertEquals(1, retornoApiList.size());
//
//		acessoRepository.deleteById(acesso.getId());
//	}
//
//	@Test
//	public void cadastraAcessoTest() {
//		Acesso acesso = new Acesso();
//		acesso.setDescricao("ROLE_BASIC");
//		Assertions.assertNull(acesso.getId());
//		acesso = acessoController.salvarAcesso(acesso).getBody();
//		Assertions.assertTrue(acesso.getId() > 0);
//	}
//
//	@Test
//	public void carregarAcessoTest() {
//		var acessos = acessoController.carregarAcessos();
//		Assertions.assertNotNull(acessos);
//		assertEquals(8, acessos.getBody().size());
//	}

}
