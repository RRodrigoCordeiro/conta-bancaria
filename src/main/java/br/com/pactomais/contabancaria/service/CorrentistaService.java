package br.com.pactomais.contabancaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pactomais.contabancaria.exception.RecursoNaoEncontradoException;
import br.com.pactomais.contabancaria.model.Correntista;
import br.com.pactomais.contabancaria.repository.CorrentistaRepository;

@Service
public class CorrentistaService {

    @Autowired
    private CorrentistaRepository correntistaRepository;

    public Correntista cadastrar(Correntista correntista) {
        return correntistaRepository.save(correntista);
    }

    public Correntista buscarPorId(Long id) {
        Correntista correntista = correntistaRepository.findById(id).orElse(null);

        if (correntista == null) {
            throw new RecursoNaoEncontradoException("Correntista nao encontrado");
        }
        return correntista;
    }
}