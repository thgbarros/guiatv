package br.ind.scenario.guiatv.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(fluent = true)
public class Wrapper<T> {
    @JsonProperty("response")
    private ResponseWrapper<T> response;
}
