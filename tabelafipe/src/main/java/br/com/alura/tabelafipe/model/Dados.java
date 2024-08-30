package br.com.alura.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public record Dados(String codigo,
                    @JsonAlias("nome") String modelo) {

    @Override
    public String toString() {
        return "codigo: '" + codigo + '\'' +
                ", modelo: '" + modelo + '\'';
    }
}
