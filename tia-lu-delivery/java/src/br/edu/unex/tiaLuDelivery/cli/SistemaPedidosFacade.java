/**
 * A classe SistemaPedidosFacade fornece uma interface unificada e simplificada
 * para o subsistema de pedidos complexo (Gerenciamento de Clientes, Cardápio, Processamento).
 */

package br.edu.unex.tiaLuDelivery.cli;

public class SistemaPedidosFacade {

    // Referências aos subsistemas que ela irá orquestrar
    private final ClienteDAO clienteDAO;
    private final CardapioManager cardapioManager;
    private final PedidoProcessor pedidoProcessor;

    public SistemaPedidosFacade() {
        // Inicializa os componentes do subsistema
        this.clienteDAO = new ClienteDAO();
        this.cardapioManager = new CardapioManager();
        this.pedidoProcessor = new PedidoProcessor();
    }

    /**
     * Método simplificado para registrar um novo cliente.
     * Esconde a necessidade de interagir diretamente com ClienteDAO.
     * @param nome O nome do cliente.
     * @param email O email do cliente.
     */
    public void registrarNovoCliente(String nome, String email) { // Exemplo do documento: registrarNovoCliente()
        System.out.println("\n--- [FACADE] Chamada: Registrar Novo Cliente ---");
        // A Facade faz o trabalho de orquestração:
        clienteDAO.salvarCliente(nome, email);
        System.out.println("[FACADE] Novo cliente " + nome + " registrado com sucesso.");
    }

    /**
     * Método simplificado para criar um pedido.
     * Esconde as verificações e o processamento inicial complexo.
     * @param clienteEmail O email do cliente que está fazendo o pedido.
     * @param itemCardapio O item do cardápio solicitado.
     * @param quantidade A quantidade do item.
     * @return true se o pedido foi iniciado, false caso contrário.
     */
    public boolean criarPedido(String clienteEmail, String itemCardapio, int quantidade) { // Exemplo do documento: criarPedido()
        System.out.println("\n--- [FACADE] Chamada: Criar Pedido ---");

        // 1. A Facade verifica a disponibilidade do item.
        if (!cardapioManager.verificarDisponibilidade(itemCardapio, quantidade)) {
            System.out.println("[FACADE] Erro: Item indisponível. Pedido CANCELADO.");
            return false;
        }

        // 2. A Facade inicia o processamento real (que usaria o padrão Builder).
        pedidoProcessor.iniciarProcessamento(clienteEmail, itemCardapio);

        System.out.println("[FACADE] Pedido criado e encaminhado para o estabelecimento.");
        return true;
    }

}