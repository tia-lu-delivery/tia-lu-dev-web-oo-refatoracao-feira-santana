package br.edu.unex.tiaLuDelivery;

public class Aplicacao {
    public static void main(String[] args) {
        // O cliente interage APENAS com a Fachada.
        SistemaPedidosFacade fachada = new SistemaPedidosFacade();

        // Operação 1: Registrar Cliente
        fachada.registrarNovoCliente("Ana Silva", "ana.silva@email.com");

        // Operação 2: Criar Pedido
        boolean sucesso = fachada.criarPedido("ana.silva@email.com", "Pizza de Calabresa", 1);

        if(sucesso) {
            System.out.println("\nResultado final: O usuário só precisou chamar um método simples para criar um pedido complexo.");
        }
    }
}