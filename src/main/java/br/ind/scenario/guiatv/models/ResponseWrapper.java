package br.ind.scenario.guiatv.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@Accessors(fluent = true)
public class ResponseWrapper<T> {
    @JsonProperty("docs")
    private Set<T> docs;
    @JsonProperty("numFound")
    private Integer totalEncontrado;
    @JsonProperty("start")
    private Integer inicio;

}
