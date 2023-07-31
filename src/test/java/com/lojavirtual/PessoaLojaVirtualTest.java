package com.lojavirtual;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lojavirtual.controller.PessoaController;
import com.lojavirtual.exception.PessoaException;
import com.lojavirtual.exceptionhandler.ControllerExceptionHandler;
import com.lojavirtual.model.Pessoa;
import com.lojavirtual.model.PessoaFisica;
import com.lojavirtual.model.PessoaJuridica;
import com.lojavirtual.utils.ValidaCNPJ;
import com.lojavirtual.utils.ValidaCPF;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

import static com.lojavirtual.utils.GeneratePojoClass.genPessoaFisica;
import static com.lojavirtual.utils.GeneratePojoClass.genPessoaJuridica;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PessoaLojaVirtualTest {

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

    @Test
    public void testSalvarPessoaFisica() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        var resultApi = this.mockMvc.perform(MockMvcRequestBuilders.post("/cadastrarPessoaFisica")
                        .content(mapper.writeValueAsString(genPessoaFisica()))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        var pessoaFisica = mapper.readValue(resultApi.andReturn().getResponse().getContentAsString(), PessoaFisica.class);

        assertTrue(pessoaFisica.getId() > 0);

        pessoaFisica.getEnderecos().forEach(endereco -> assertTrue(endereco.getId() > 0));

        assertEquals(2, pessoaFisica.getEnderecos().size());

    }

    @Test
    public void testSalvarPessoaJuridica() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        var resultApi = this.mockMvc.perform(MockMvcRequestBuilders.post("/cadastrarPessoaJuridica")
                .content(mapper.writeValueAsString(genPessoaJuridica()))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        var pessoaJuridica = mapper.readValue(resultApi.andReturn().getResponse().getContentAsString(), PessoaJuridica.class);

        assertTrue(pessoaJuridica.getId() > 0);

        pessoaJuridica.getEnderecos().forEach(endereco -> assertTrue(endereco.getId() > 0));

        assertEquals(1, pessoaJuridica.getEnderecos().size());

    }

    @Test
    public void testObterBadRequestPessoaJuridicaException() throws Exception {
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setCnpj("56.736.280/0001-22");

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/cadastrarPessoaJuridica")
                        .content(mapper.writeValueAsString(pessoaJuridica))
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof PessoaException))
                .andExpect(result -> Assertions.assertEquals("Este CNPJ: 56736280000122 e invalido!",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testObterBadRequestPessoaFisicaException() throws Exception {
        var pessoaFisica = PessoaFisica.builder().cpf("925.374.950-4");

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/cadastrarPessoaFisica")
                        .content(mapper.writeValueAsString(pessoaFisica))
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof PessoaException))
                .andExpect(result -> Assertions.assertEquals("Formato invalido",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void validarCnpjTest() {
        boolean isCnpj = ValidaCNPJ.isCNPJ("60.736.280/0001-22");
        assertTrue(isCnpj);
    }

    @Test
    public void validarCpfTest() {
        boolean isCpf = ValidaCPF.isCPF("925.374.950-47");
        assertTrue(isCpf);
    }

    @Test
    public void validarNaoCnpjTest() {
        boolean isCnpj = ValidaCNPJ.isCNPJ("56.736.280/0001-22");
        assertFalse(isCnpj);
    }

    @Test
    public void validarNaoCpfTest() {
        boolean isCpf = ValidaCPF.isCPF("925.374.950-4");
        assertFalse(isCpf);
    }
}
