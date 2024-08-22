package com.vrbeneficios.miniautorizador.controller;

import com.vrbeneficios.miniautorizador.service.TransacaoService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<String> realizarTransacao(@RequestBody TransacaoRequest request) {
        try {
            transacaoService.autorizarTransacao(request.getNumeroCartao(), request.getSenhaCartao(), request.getValor());
            return new ResponseEntity<>("OK", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Getter
    @Setter
    class TransacaoRequest {
        private String numeroCartao;
        private String senhaCartao;
        private double valor;

    }
}
