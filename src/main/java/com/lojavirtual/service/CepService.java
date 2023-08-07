package com.lojavirtual.service;

import com.lojavirtual.dto.CepDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class CepService {
    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/{0}/json/";
    private final WebClient.Builder webClientBuilder;
    public CepDTO consultaCep(String cep) {
        return webClientBuilder.build()
                .get()
                .uri(MessageFormat.format(VIA_CEP_URL, cep))
                .retrieve()
                .bodyToMono(CepDTO.class)
                .block();
    }
}
