package com.vrbeneficios.miniautorizador.service;

import com.vrbeneficios.miniautorizador.model.Cartao;
import com.vrbeneficios.miniautorizador.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    public void autorizarTransacao(String numeroCartao, String senha, double valor) {
        Cartao cartao = cartaoRepository.findById(numeroCartao)
                .orElseThrow(() -> new RuntimeException("Cartão inexistente"));

        if (!cartao.getSenha().equals(senha)) {
            throw new RuntimeException("Senha inválida");
        }

        if (cartao.getSaldo() < valor) {
            throw new RuntimeException("Saldo insuficiente");
        }

        cartao.setSaldo(cartao.getSaldo() - valor);
        cartaoRepository.save(cartao);
    }
}
