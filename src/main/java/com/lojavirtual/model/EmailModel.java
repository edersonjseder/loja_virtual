package com.lojavirtual.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailModel {
    private String subject;
    private String sendTo;
    private String sentFrom;
    private String message;
    private String attachment;
}
