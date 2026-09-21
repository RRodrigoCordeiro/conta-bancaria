package br.com.pactomais.contabancaria.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pactomais.contabancaria.exception.RecursoNaoEncontradoException;
import br.com.pactomais.contabancaria.model.Conta;
import br.com.pactomais.contabancaria.model.ContaCorrente;
import br.com.pactomais.contabancaria.model.ContaPoupanca;
import br.com.pactomais.contabancaria.model.Correntista;
import br.com.pactomais.contabancaria.repository.ContaRepository;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private CorrentistaService correntistaService;

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
}