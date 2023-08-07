package com.lojavirtual.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingDto implements Serializable {
    private Boolean free;
    private Boolean database;
}
