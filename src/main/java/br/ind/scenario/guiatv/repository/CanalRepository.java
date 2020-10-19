package br.ind.scenario.guiatv.repository;

import br.ind.scenario.guiatv.models.Canal;
import br.ind.scenario.guiatv.models.Wrapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CanalRepository {

    private static final String CANAIS_URL = "https://programacao.netcombo.com.br/gatekeeper/canal/select?q=id_cidade:%d&wt=json&start=%d&rows=%d&sort=cn_canal asc";
    private Set<Canal> cache;

    public Set<Canal> loadCanais(Integer cidadeId, Integer inicio, Integer total) {
        if (cache == null) {
            String url = String.format(CANAIS_URL, cidadeId, inicio, total);
            RestTemplate restTemplate = new RestTemplate();

            Wrapper<Canal> canalWrapper = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<Wrapper<Canal>>() {
                    }).getBody();
            cache = Objects.requireNonNull(canalWrapper).response().docs();
            cache = cache.stream().filter(canal -> canal.id() != null).collect(Collectors.toSet());
        }
        return cache;
    }

    public Canal getCanalPeloId(Integer cidadeId, String canalId) {
        Set<Canal> canais = loadCanais(cidadeId, 0, 1000);
        return canais.stream().filter(canal -> canal.id().equals(canalId)).findFirst().orElse(null);
    }

}
