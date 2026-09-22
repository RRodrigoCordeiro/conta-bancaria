package br.com.pactomais.contabancaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pactomais.contabancaria.model.Conta;
import br.com.pactomais.contabancaria.model.ContaCorrente;
import br.com.pactomais.contabancaria.model.ContaPoupanca;
import br.com.pactomais.contabancaria.model.Transacao;
import br.com.pactomais.contabancaria.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/corrente/{correntistaId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ContaCorrente abrirContaCorrente(@PathVariable Long correntistaId, @RequestBody ContaCorrente conta) {
        return contaService.abrirContaCorrente(correntistaId, conta);
    }

    @PostMapping("/poupanca/{correntistaId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ContaPoupanca abrirContaPoupanca(@PathVariable Long correntistaId, @RequestBody ContaPoupanca conta) {
        return contaService.abrirContaPoupanca(correntistaId, conta);
    }

    @GetMapping("/{id}")
    public Conta buscarPorId(@PathVariable Long id) {
        return contaService.buscarPorId(id);
    }

    @PostMapping("/{id}/depositos")
    @ResponseStatus(HttpStatus.CREATED)
    public Transacao depositar(@PathVariable Long id, @RequestBody Transacao transacao) {
        return contaService.depositar(id, transacao.getValor());
    }

    @PostMapping("/{id}/saques")
    @ResponseStatus(HttpStatus.CREATED)
    public Transacao sacar(@PathVariable Long id, @RequestBody Transacao transacao) {
        return contaService.sacar(id, transacao.getValor());
    }

    @GetMapping("/{id}/extrato")
    public List<Transacao> extrato(@PathVariable Long id) {
        return contaService.extrato(id);
    }
}