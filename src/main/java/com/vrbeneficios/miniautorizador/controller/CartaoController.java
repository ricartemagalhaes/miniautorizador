package com.vrbeneficios.miniautorizador.controller;

import com.vrbeneficios.miniautorizador.model.Cartao;
import com.vrbeneficios.miniautorizador.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    public ResponseEntity<Cartao> criarCartao(@RequestBody Cartao cartao) {
        try {
            Cartao novoCartao = cartaoService.criarCartao(cartao);
            return new ResponseEntity<>(novoCartao, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<Double> obterSaldo(@PathVariable String numeroCartao) {
        try {
            double saldo = cartaoService.obterSaldo(numeroCartao);
            return new ResponseEntity<>(saldo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
