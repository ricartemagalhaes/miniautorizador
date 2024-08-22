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

class CartaoServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private CartaoService cartaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarCartaoComSucesso() {
        Cartao cartao = new Cartao("6549873025634501", "1234", 500.0);

        when(cartaoRepository.existsById(cartao.getNumeroCartao())).thenReturn(false);
        when(cartaoRepository.save(cartao)).thenReturn(cartao);

        Cartao result = cartaoService.criarCartao(cartao);

        assertNotNull(result);
        assertEquals(500.0, result.getSaldo());
        verify(cartaoRepository, times(1)).save(cartao);
    }

    @Test
    void criarCartaoJaExistente() {
        Cartao cartao = new Cartao("6549873025634501", "1234", 500.0);

        when(cartaoRepository.existsById(cartao.getNumeroCartao())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> cartaoService.criarCartao(cartao));
        verify(cartaoRepository, never()).save(cartao);
    }

    @Test
    void obterSaldoComSucesso() {
        Cartao cartao = new Cartao("6549873025634501", "1234", 500.0);

        when(cartaoRepository.findById(cartao.getNumeroCartao())).thenReturn(Optional.of(cartao));

        double saldo = cartaoService.obterSaldo(cartao.getNumeroCartao());

        assertEquals(500.0, saldo);
        verify(cartaoRepository, times(1)).findById(cartao.getNumeroCartao());
    }

    @Test
    void obterSaldoCartaoNaoExistente() {
        when(cartaoRepository.findById("6549873025634501")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> cartaoService.obterSaldo("6549873025634501"));
        verify(cartaoRepository, times(1)).findById("6549873025634501");
    }
}
