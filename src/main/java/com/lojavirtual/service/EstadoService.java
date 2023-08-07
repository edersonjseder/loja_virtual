package com.lojavirtual.service;

import com.lojavirtual.dto.EstadoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class EstadoService {
    private static final String IBGE_ESTADOS_URL = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/{0}";
    private final WebClient.Builder webClientBuilder;
    public EstadoDto buscarEstadoPorUf(String uf) {
        return webClientBuilder.build()
                .get()
                .uri(MessageFormat.format(IBGE_ESTADOS_URL, uf))
                .retrieve()
                .bodyToMono(EstadoDto.class)
                .block();
    }
}
