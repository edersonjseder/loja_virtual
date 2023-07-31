package com.lojavirtual.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTokenResponse {
    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "dataAtualSenha")
    private String dataAtualSenha;

    @JsonProperty(value = "Authorization")
    private String authorization;
}
