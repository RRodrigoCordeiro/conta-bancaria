package br.com.pactomais.contabancaria.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.pactomais.contabancaria.model.Correntista;

public interface CorrentistaRepository extends JpaRepository<Correntista, Long> {
}