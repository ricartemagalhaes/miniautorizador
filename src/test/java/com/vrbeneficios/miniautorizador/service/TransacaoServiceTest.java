package com.vrbeneficios.miniautorizador.service;

import com.vrbeneficios.miniautorizador.model.Cartao;
import com.vrbeneficios.miniautorizador.repository.CartaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransacaoServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private TransacaoService transacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void autorizarTransacaoComSucesso() {
        Cartao cartao = new Cartao("6549873025634501", "1234", 500.0);

        when(cartaoRepository.findById(cartao.getNumeroCartao())).thenReturn(Optional.of(cartao));

        transacaoService.autorizarTransacao(cartao.getNumeroCartao(), cartao.getSenha(), 100.0);

        assertEquals(400.0, cartao.getSaldo());
        verify(cartaoRepository, times(1)).save(cartao);
    }

    @Test
    void autorizarTransacaoCartaoInexistente() {
        when(cartaoRepository.findById("6549873025634501")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> transacaoService.autorizarTransacao("6549873025634501", "1234", 100.0));
        verify(cartaoRepository, never()).save(any(Cartao.class));
    }

    @Test
    void autorizarTransacaoSenhaInvalida() {
        Cartao cartao = new Cartao("6549873025634501", "1234", 500.0);

        when(cartaoRepository.findById(cartao.getNumeroCartao())).thenReturn(Optional.of(cartao));

        assertThrows(RuntimeException.class, () -> transacaoService.autorizarTransacao(cartao.getNumeroCartao(), "0000", 100.0));
        verify(cartaoRepository, never()).save(cartao);
    }

    @Test
    void autorizarTransacaoSaldoInsuficiente() {
        Cartao cartao = new Cartao("6549873025634501", "1234", 50.0);

        when(cartaoRepository.findById(cartao.getNumeroCartao())).thenReturn(Optional.of(cartao));

        assertThrows(RuntimeException.class, () -> transacaoService.autorizarTransacao(cartao.getNumeroCartao(), cartao.getSenha(), 100.0));
        verify(cartaoRepository, never()).save(cartao);
    }
}
