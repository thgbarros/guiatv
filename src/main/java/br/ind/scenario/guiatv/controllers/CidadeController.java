package br.ind.scenario.guiatv.controllers;

import br.ind.scenario.guiatv.models.Cidade;
import br.ind.scenario.guiatv.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
public class CidadeController {

    @Autowired
    private CidadeRepository repository;


    @GetMapping("/cidades")
    public Set<Cidade> todas() {
        return repository.loadCidades();
    }

    @GetMapping("/cidades/{idCidade}")
    public Cidade cidade(@PathVariable Integer idCidade) {
        Set<Cidade> cidades = repository.loadCidades();
        Optional<Cidade> cidadeOptional = cidades.stream().filter(cidade -> cidade.id().equals(idCidade)).findFirst();
        return cidadeOptional.orElse(null);
    }


}
