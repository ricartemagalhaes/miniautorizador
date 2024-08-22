package com.vrbeneficios.miniautorizador.service;

import com.vrbeneficios.miniautorizador.model.Cartao;
import com.vrbeneficios.miniautorizador.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    public Cartao criarCartao(Cartao cartao) {
        if (cartaoRepository.existsById(cartao.getNumeroCartao())) {
            throw new RuntimeException("Cartão já existe!");
        }
        return cartaoRepository.save(cartao);
    }

    public double obterSaldo(String numeroCartao) {
        Optional<Cartao> cartao = cartaoRepository.findById(numeroCartao);
        if (cartao.isPresent()) {
            return cartao.get().getSaldo();
        } else {
            throw new RuntimeException("Cartão não encontrado!");
        }
    }
}
