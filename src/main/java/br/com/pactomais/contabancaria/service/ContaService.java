package br.com.pactomais.contabancaria.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.pactomais.contabancaria.exception.RecursoNaoEncontradoException;
import br.com.pactomais.contabancaria.model.Conta;
import br.com.pactomais.contabancaria.model.ContaCorrente;
import br.com.pactomais.contabancaria.model.ContaPoupanca;
import br.com.pactomais.contabancaria.model.Correntista;
import br.com.pactomais.contabancaria.model.TipoTransacao;
import br.com.pactomais.contabancaria.model.Transacao;
import br.com.pactomais.contabancaria.repository.ContaRepository;
import br.com.pactomais.contabancaria.repository.TransacaoRepository;
import java.util.ArrayList;
import br.com.pactomais.contabancaria.dto.TransacaoResponse;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private CorrentistaService correntistaService;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public ContaCorrente abrirContaCorrente(Long correntistaId, ContaCorrente conta) {
        Correntista correntista = correntistaService.buscarPorId(correntistaId);
        conta.setCorrentista(correntista);
        conta.setSaldo(BigDecimal.ZERO);
        return contaRepository.save(conta);
    }

    public ContaPoupanca abrirContaPoupanca(Long correntistaId, ContaPoupanca conta) {
        Correntista correntista = correntistaService.buscarPorId(correntistaId);
        conta.setCorrentista(correntista);
        conta.setSaldo(BigDecimal.ZERO);
        return contaRepository.save(conta);
    }

    public Conta buscarPorId(Long id) {
        Conta conta = contaRepository.findById(id).orElse(null);

        if (conta == null) {
            throw new RecursoNaoEncontradoException("Conta nao encontrada");
        }
        return conta;
    }

    public Transacao depositar(Long contaId, BigDecimal valor) {
        Conta conta = buscarPorId(contaId);
        conta.depositar(valor);
        contaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setTipo(TipoTransacao.DEPOSITO);
        transacao.setValor(valor);
        transacao.setData(LocalDateTime.now());
        return transacaoRepository.save(transacao);
    }

    public Transacao sacar(Long contaId, BigDecimal valor) {
        Conta conta = buscarPorId(contaId);
        conta.sacar(valor);
        contaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setTipo(TipoTransacao.SAQUE);
        transacao.setValor(valor);
        transacao.setData(LocalDateTime.now());
        return transacaoRepository.save(transacao);
    }

    public List<TransacaoResponse> extrato(Long contaId) {
        buscarPorId(contaId);
        List<Transacao> transacoes = transacaoRepository.findByContaId(contaId);

        List<TransacaoResponse> resposta = new ArrayList<>();
        for (Transacao transacao : transacoes) {
            resposta.add(new TransacaoResponse(transacao));
        }
        return resposta;
    }
}