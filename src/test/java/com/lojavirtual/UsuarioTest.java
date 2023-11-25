package com.lojavirtual;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lojavirtual.controller.PessoaController;
import com.lojavirtual.model.PessoaFisica;
import com.lojavirtual.model.Usuario;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.lojavirtual.utils.GeneratePojoClass.genPessoaFisica;
import static com.lojavirtual.utils.GeneratePojoClass.getSignUpRequest;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioTest {
    @Autowired
    private PessoaController pessoaController;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

//    @Test
//    public void testcadastrarUsuario() throws Exception {
//
//        ObjectMapper mapperPf = new ObjectMapper();
//        mapperPf.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//
//        var resultApiPf = this.mockMvc.perform(MockMvcRequestBuilders.post("/cadastrarPessoaFisica")
//                .content(mapperPf.writeValueAsString(genPessoaFisica()))
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        var pessoaFisica = mapperPf.readValue(resultApiPf.andReturn().getResponse().getContentAsString(), PessoaFisica.class);
//
//
//        ObjectMapper mapperUsr = new ObjectMapper();
//        mapperUsr.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//
//        var usuario = getSignUpRequest();
//        usuario.setEmail(pessoaFisica.getEmail());
//
//        var resultApiUsr = this.mockMvc.perform(MockMvcRequestBuilders.post("/signup")
//                .content(mapperPf.writeValueAsString(usuario))
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        var usuarioRegistrado = mapperPf.readValue(resultApiUsr.andReturn().getResponse().getContentAsString(), Usuario.class);
//    }
}
