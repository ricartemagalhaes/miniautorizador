package com.vrbeneficios.miniautorizador.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cartao {

    @Id
    private String numeroCartao;
    private String senha;
    private double saldo = 500.0;
}
