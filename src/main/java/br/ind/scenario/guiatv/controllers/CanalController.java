package br.ind.scenario.guiatv.controllers;

import br.ind.scenario.guiatv.models.Canal;
import br.ind.scenario.guiatv.repository.CanalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Set;

@RestController
public class CanalController {

    @Autowired
    private CanalRepository canalRepository;

    @CrossOrigin(origins = "*")
    @GetMapping("/canais/cidade/{cidadeId}")
    @ExceptionHandler(UnsupportedEncodingException.class)
    private Set<Canal> canais(@PathVariable Integer cidadeId,
                              @RequestParam(value = "inicio", defaultValue = "0") Integer inicio,
                              @RequestParam(value = "total", defaultValue = "10") Integer total) throws UnsupportedEncodingException {
        return canalRepository.loadCanais(cidadeId, inicio, total);
    }

}
