package br.ind.scenario.guiatv.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@NoArgsConstructor
@Accessors(fluent = true)
@Getter
@Setter
public class Programacao {
    @JsonProperty
    private String titulo;
    @JsonProperty
    @JsonAlias("genero")
    private String categoria;
    @JsonAlias("id_canal")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String canalId;
    @JsonProperty
    private Canal canal;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonAlias("id_cidade")
    private Integer cidadeId;
    @JsonProperty
    private Cidade cidade;
    @JsonProperty
    @JsonAlias("dh_inicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm[:ss]'Z'")
    private LocalDateTime inicio;
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm[:ss]'Z'")
    @JsonAlias("dh_fim")
    private LocalDateTime fim;

}
