package br.com.alura.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DescricaoCarro(@JsonAlias("TipoVeiculo") String tipoVeiculo,
                             @JsonAlias("Valor") String valor,
                             @JsonAlias("Marca") String marca,
                             @JsonAlias("Modelo") String modelo,
                             @JsonAlias("AnoModelo") Integer anoModelo,
                             @JsonAlias("Combustivel") String combustivel
                             ) {
    @Override
    public String toString() {
        return "\n" + "tipoVeiculo: " + tipoVeiculo + '\n' +
                "valor: " + valor + '\n' +
                "marca: " + marca + '\n' +
                "modelo: " + modelo + '\n' +
                "anoModelo: " + anoModelo + "\n" +
                "combustivel: " + combustivel + "\n";
    }
}
