package br.ind.scenario.guiatv.repository;

import br.ind.scenario.guiatv.models.Cidade;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CidadeRepository {

    private static final String CIDADE_URL = "https://www.net.com.br/sites/api/cidades";
    private Set<Cidade> cache;


    public Set<Cidade> loadCidades() {
        if (cache == null) {
            RestTemplate restTemplate = new RestTemplate();

            cache = restTemplate.exchange(CIDADE_URL, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Set<Cidade>>() {
                    }).getBody();
            cache = Objects.requireNonNull(cache).stream()
                    .filter(cidade -> cidade.id() != null).collect(Collectors.toSet());
        }

        return cache;
    }

    public Cidade getCidadePeloId(Integer id) {
        return loadCidades().stream().filter(cidade -> cidade.id().equals(id)).findFirst().orElse(null);
    }

}
