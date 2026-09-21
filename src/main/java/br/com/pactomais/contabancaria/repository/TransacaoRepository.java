package br.com.pactomais.contabancaria.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.pactomais.contabancaria.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findByContaId(Long contaId);
}