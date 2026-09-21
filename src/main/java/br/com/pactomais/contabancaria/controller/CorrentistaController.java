package br.com.pactomais.contabancaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pactomais.contabancaria.model.Correntista;
import br.com.pactomais.contabancaria.service.CorrentistaService;

@RestController
@RequestMapping("/correntistas")
public class CorrentistaController {

    @Autowired
    private CorrentistaService correntistaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Correntista cadastrar(@RequestBody Correntista correntista) {
        return correntistaService.cadastrar(correntista);
    }

    @GetMapping("/{id}")
    public Correntista buscarPorId(@PathVariable Long id) {
        return correntistaService.buscarPorId(id);
    }
}