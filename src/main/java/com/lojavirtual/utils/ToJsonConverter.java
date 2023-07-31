package com.lojavirtual.utils;

import com.google.gson.Gson;
import com.lojavirtual.response.TokenResponse;
import org.springframework.stereotype.Component;

@Component
public class ToJsonConverter {
    public String toJson(String token) {
        Gson gson = new Gson();
        return gson.toJson(new TokenResponse(token));
    }
}
