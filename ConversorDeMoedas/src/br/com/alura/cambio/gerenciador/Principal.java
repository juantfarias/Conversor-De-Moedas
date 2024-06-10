package br.com.alura.cambio.gerenciador;

import br.com.alura.cambio.ApiRequest.CalculadoraConversao;
import br.com.alura.cambio.ApiRequest.SolicitadorTaxaCambio;

import java.io.IOException;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Double paridade = 0.0;

        SolicitadorTaxaCambio solicitadorTaxaCambio = new SolicitadorTaxaCambio();
        CalculadoraConversao calculadoraConversao = new CalculadoraConversao();

        Scanner scanner = new Scanner(System.in);

        boolean sair = false;

        Map<String, String[]> opcoesCambio = new HashMap<>();
        opcoesCambio.put("1", new String[]{"BRL", "EUR"});
        opcoesCambio.put("2", new String[]{"EUR", "BRL"});
        opcoesCambio.put("3", new String[]{"BRL", "USD"});
        opcoesCambio.put("4", new String[]{"USD", "BRL"});
        opcoesCambio.put("5", new String[]{"BRL", "KWR"});
        opcoesCambio.put("6", new String[]{"KWR", "BRL"});

        do {
            MenuOpcoes.mostrarMenu();
            try {
                var opcao = scanner.nextLine();
                if (opcoesCambio.containsKey(opcao)) {
                    String[] moedas = opcoesCambio.get(opcao);
                    paridade = solicitadorTaxaCambio.receberValor(moedas[0], moedas[1]);
                    calculadoraConversao.CalculadoraParidade(moedas[0], moedas[1], paridade);
                } else if ("0".equals(opcao)) {
                    System.out.println("Saindo do programa....");
                    sair = true;
                } else {
                    mostrarMensagemOpcaoInvalida();
                }
            } catch (InputMismatchException e) {
                mostrarMensagemErro("Erro: Por favor, insira um valor numérico válido (Se estiver usando \"ponto\" como casa decimal, troque pela vírgula!).");
            } catch (ConnectException e) {
                mostrarMensagemErro("Erro de conexão, conecte a internet");
            }
        } while (!sair);
    }

    private static void mostrarMensagemOpcaoInvalida() {
        System.out.println("Opção inválida! Por favor, selecione uma opção válida.");
        System.out.println("Pressione Enter para continuar...");
        new Scanner(System.in).nextLine();
    }

    private static void mostrarMensagemErro(String mensagem) {
        System.out.println(mensagem);
        System.out.println("Pressione Enter para continuar...");
        new Scanner(System.in).nextLine();
    }
}
