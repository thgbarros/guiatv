package br.ind.scenario.guiatv.controllers;

import br.ind.scenario.guiatv.models.Cidade;
import br.ind.scenario.guiatv.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class EstadoController {

    @Autowired
    private CidadeRepository repository;

    @GetMapping("/estados")
    public Set<String> estados() {
        Set<Cidade> cidades = repository.loadCidades();
        return cidades.stream().map(Cidade::estado).filter(estados -> !estados.isEmpty()).collect(Collectors.toSet());
    }

    @GetMapping("/estados/{estado}")
    public Set<Cidade> cidadePorEstado(@PathVariable String estado) {
        Set<Cidade> cidades = repository.loadCidades();
        return cidades.stream().filter(cidade -> cidade.estado().equalsIgnoreCase(estado)).collect(Collectors.toSet());
    }

}
