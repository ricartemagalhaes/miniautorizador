package com.vrbeneficios.miniautorizador.repository;

import com.vrbeneficios.miniautorizador.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, String> {
}
