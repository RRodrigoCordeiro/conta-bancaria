package br.com.pactomais.contabancaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.pactomais.contabancaria.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}