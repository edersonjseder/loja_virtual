package com.lojavirtual.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import static com.lojavirtual.constants.DBConstants.UPDATE_ACESSO_END_POINT;

@Service
@RequiredArgsConstructor
public class ContagemAcessoApiService {
    private final JdbcTemplate jdbcTemplate;

    /**
     * Controls the access to the end points of our API
     */
    public void atualizaAcessoEndPoint() {
        jdbcTemplate.execute(UPDATE_ACESSO_END_POINT);
    }
}
