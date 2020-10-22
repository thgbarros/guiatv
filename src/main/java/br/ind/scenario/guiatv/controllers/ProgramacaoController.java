package br.ind.scenario.guiatv.controllers;

import br.ind.scenario.guiatv.models.Programacao;
import br.ind.scenario.guiatv.models.Wrapper;
import br.ind.scenario.guiatv.repository.CanalRepository;
import br.ind.scenario.guiatv.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;

@RestController
public class ProgramacaoController {

    private static final String PROGRAMACAO_URL = "https://programacao.netcombo.com.br/gatekeeper/exibicao/select?" +
            "q=id_revel:%s&wt=json&rows=%d&sort=id_canal asc,dh_inicio asc&fq=dh_inicio:[%s TO %s]";
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private CanalRepository canalRepository;


    @GetMapping("/programacao/{canalId}")
    @ExceptionHandler(UnsupportedEncodingException.class)
    private Set<Programacao> programacoes(
            @PathVariable String canalId,
            @RequestParam(value = "total", defaultValue = "10") Integer total,
            @RequestParam(value = "dataInicio", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam(value = "dataFim", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) throws UnsupportedEncodingException {

        Instant instantInicio = (dataInicio == null) ?
                LocalDate.now().atTime(LocalTime.MIN).toInstant(ZoneOffset.UTC) : dataInicio.toInstant(ZoneOffset.UTC);
        Instant instantFim = (dataFim == null) ?
                LocalDate.now().atTime(LocalTime.MAX).withNano(0).toInstant(ZoneOffset.UTC) : dataFim.toInstant(ZoneOffset.UTC);

        String url = String.format(PROGRAMACAO_URL, canalId, total, formatter.format(instantInicio), formatter.format(instantFim));
        RestTemplate restTemplate = new RestTemplate();

        Wrapper<Programacao> programacaoWrapper = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Wrapper<Programacao>>() {
                }).getBody();
        Set<Programacao> programacoes = Objects.requireNonNull(programacaoWrapper).response().docs();
        programacoes.forEach(programacao -> {
            programacao.cidade(cidadeRepository.getCidadePeloId(programacao.cidadeId()));
            programacao.canal(canalRepository.getCanalPeloId(programacao.cidadeId(), programacao.canalId()));
        });

        return programacoes;
    }

}
