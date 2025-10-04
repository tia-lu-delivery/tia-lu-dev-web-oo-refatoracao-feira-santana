public class PedidoProcessor {
    public void iniciarProcessamento(String clienteEmail, String item) {
        System.out.println("Processor: Iniciando processamento do pedido para " + clienteEmail + " com item: " + item);
        // Lógica complexa de validação e criação inicial do Pedido (usando Builder, State, etc.)
        System.out.println("Pedido criado e no estado: AGUARDANDO ACEITE DO ESTABELECIMENTO");
    }
}