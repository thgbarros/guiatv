package br.ind.scenario.guiatv.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(fluent = true)
@Getter
@Setter
public class Canal {
    @JsonAlias("id_canal")
    @JsonProperty
    private String id;
    @JsonProperty("nome")
    private String nome;
    @JsonAlias("cn_canal")
    @JsonProperty("canal")
    private String numero;
    @JsonAlias("id_revel")
    private String grade;
    @JsonAlias("url_imagem")
    @JsonProperty
    private String imagem;
}
