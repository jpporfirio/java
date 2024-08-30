package br.com.alura.tabelafipe.principal;

import br.com.alura.tabelafipe.model.Dados;
import br.com.alura.tabelafipe.model.DescricaoCarro;
import br.com.alura.tabelafipe.model.Modelos;
import br.com.alura.tabelafipe.service.ConsumidorApi;
import br.com.alura.tabelafipe.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner scan = new Scanner(System.in);
    private ConsumidorApi consumir = new ConsumidorApi();
    private ConverteDados converteDados = new ConverteDados();
    private String endereco;
    private String json;

    private final String BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void menu() {

        String consulta = "******* OPÇÕES *******" +
                "\n Carro" +
                "\n Moto" +
                "\n Caminhão \n" +
                "\n Digite uma das opções  para consultar valores: ";
        System.out.println(consulta);
        String veiculo = scan.nextLine().toLowerCase();


        if (veiculo.toLowerCase().contains("carr")) {
            endereco = BASE + "carros/marcas";
        } else if (veiculo.toLowerCase().contains("mot")) {
            endereco = BASE + "motos/marcas";
        } else{
            endereco = "caminhoes/marcas";
        }

        json = consumir.comsumindoApi(endereco);
        System.out.println(json);
        List<Dados> marcas = converteDados.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::modelo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca que deseja pesquisar");
        String codigoMarca = scan.nextLine();

        endereco += "/" + codigoMarca + "/modelos";

        json = consumir.comsumindoApi(endereco);

        Modelos modelos = converteDados.obterDados(json, Modelos.class);

        modelos.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Digite um trecho do nome do carro a ser buscado: ");
        String nomeVeiculo = scan.nextLine();

        List<Dados> modelosFiltrados = modelos.modelos().stream()
                .filter(v -> v.modelo().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toUnmodifiableList());

        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite por favor o codigo do modelo: ");
        var codigoModelo = scan.nextLine();

        endereco += "/" + codigoModelo + "/anos";
        json = consumir.comsumindoApi(endereco);
        List<Dados> anosVeiculo = converteDados.obterLista(json, Dados.class);
        List <DescricaoCarro> veiculos = new ArrayList<>();

        for (int i = 0; i < anosVeiculo.size() ; i++) {
            var enderecoAnos = endereco + "/" + anosVeiculo.get(i).codigo();
            json = consumir.comsumindoApi(enderecoAnos);
            DescricaoCarro descricaoCarro = converteDados.obterDados(json, DescricaoCarro.class);
            veiculos.add(descricaoCarro);
        }
        System.out.println("\n Todos os veiculos filtrados por anos");
        veiculos.forEach(System.out::println);


//
//        System.out.println("Digite o ano do veiculo que deseja consultar: ");
//        String anoEscolhido = scan.nextLine();
//
//        endereco += "/" + anoEscolhido + "-1";
//        json = consumir.comsumindoApi(endereco);
//        DescricaoCarro veiculoEscolhido = converteDados.obterDados(json, DescricaoCarro.class);
//
//        System.out.println(veiculoEscolhido);

    }
}