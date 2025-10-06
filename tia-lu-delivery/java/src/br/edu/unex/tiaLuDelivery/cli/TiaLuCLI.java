package br.edu.unex.tiaLuDelivery.cli;

import br.edu.unex.tiaLuDelivery.exceptions.StateInvalidException;
import br.edu.unex.tiaLuDelivery.model.Customer;
import br.edu.unex.tiaLuDelivery.model.Order;

import java.util.Scanner;

public class TiaLuCLI {

    public void start() {

        Scanner scanner = new Scanner(System.in);
        Customer cliente = new Customer("Gabriel Torres");
        Order pedido = new Order(cliente);

        System.out.println("1. Pedido criado:");
        exibirStatusPedido(pedido);
        System.out.println();

        System.out.println("2. Estabelecimento aceita o pedido:");
        pedido.accept();
        exibirStatusPedido(pedido);
        System.out.println();
        
        System.out.println("3. Iniciando preparo:");
        pedido.startPreparation();
        exibirStatusPedido(pedido);
        System.out.println();

        // testando mensagem de erro ao sair da lógica
        System.out.println("4. Entregue");
        try {
            pedido.confirmDelivery();
        } catch (StateInvalidException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        exibirStatusPedido(pedido);
        System.out.println();

        System.out.println("4. Finalizando preparo:");
        pedido.finishPreparation();
        exibirStatusPedido(pedido);
        System.out.println();

        System.out.println("5. Enviando para entrega:");
        pedido.sendForDelivery();
        exibirStatusPedido(pedido);

    }

    private static void exibirStatusPedido(Order order) {
        System.out.println("📦 Pedido " + order.getId() + " - Status: " +
                order.getCurrentStatus());
    }
}
