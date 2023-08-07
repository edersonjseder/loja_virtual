package com.lojavirtual.service;

import com.lojavirtual.dto.ConsultaCnpjPjDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class ConsultaCnpjService {
    private static final String RECEITA_CONSULTA_CNPJ_URL = "https://receitaws.com.br/v1/cnpj/{0}";
    private final WebClient.Builder webClientBuilder;

    public ConsultaCnpjPjDto consultaCnpjReceitaFederal(String cnpj) {
        return webClientBuilder.build()
                .get()
                .uri(MessageFormat.format(RECEITA_CONSULTA_CNPJ_URL, cnpj))
                .retrieve()
                .bodyToMono(ConsultaCnpjPjDto.class)
                .block();
    }
}
