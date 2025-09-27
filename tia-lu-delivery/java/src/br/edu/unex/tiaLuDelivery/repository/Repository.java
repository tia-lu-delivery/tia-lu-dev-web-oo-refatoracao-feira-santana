package br.edu.unex.tiaLuDelivery.repository;

import br.edu.unex.tiaLuDelivery.model.Customer;
import br.edu.unex.tiaLuDelivery.model.MenuItem;
import br.edu.unex.tiaLuDelivery.model.Order;
import br.edu.unex.tiaLuDelivery.model.OrderItem;
import br.edu.unex.tiaLuDelivery.model.OrderStatus;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// Mantemos a classe 'final' para evitar que alguém tente estender e quebrar nosso Singleton.
public final class Repository {

    // Aqui guardamos a nossa única instância do Repository.
    private static volatile Repository instance;

    // --- Nossas listas internas que armazenam os dados do sistema ---
    private final List<Customer> customers;
    private final List<MenuItem> menuItems;
    private final List<Order> orders;
    private final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    // Construtor privado: aqui garantimos que ninguém fora da classe consiga criar novas instâncias.
    private Repository() {
        this.customers = new ArrayList<>();
        this.menuItems = new ArrayList<>();
        this.orders = new ArrayList<>();
        System.out.println("Repository inicializado. Pronto para uso!");
    }

    // --- Método de acesso global ---
    // Aqui fizemos a lógica do Singleton: qualquer parte do sistema que precisar do Repository vai usar este método.
    public static synchronized Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    // --- Getters ---
    // Aqui fizemos cópias das listas internas para garantir que ninguém altere nossos dados por fora.
    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    public List<MenuItem> getMenuItems() {
        return new ArrayList<>(menuItems);
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    // --- MÉTODOS DE NEGÓCIO ---

    // Adicionamos um cliente, evitando duplicidade pelo telefone.
    public boolean adicionarCliente(String nome, String telefone) {
        // Aqui verificamos se algum cliente já tem o mesmo telefone.
        for (Customer c : customers) {
            if (c.getPhoneNumber() != null && c.getPhoneNumber().equals(telefone)) {
                System.out.println("Falha ao adicionar cliente: Telefone já cadastrado!");
                return false;
            }
        }
        // Se não existir, aqui fizemos a criação e adicionamos o cliente na lista.
        Customer cliente = new Customer(nome, telefone);
        customers.add(cliente);
        System.out.println("Cliente " + nome + " adicionado com sucesso!");
        return true;
    }

    // Listamos os clientes cadastrados de forma clara.
    public void listarClientes() {
        if (customers.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        System.out.println("=== CLIENTES ===");
        // Aqui fizemos a exibição de cada cliente mostrando ID, nome e telefone.
        for (Customer c : customers) {
            System.out.printf("%d | Nome: %s | Telefone: %s%n",
                    c.getId(), c.getName(), c.getPhoneNumber());
        }
    }

    // Adicionamos um item ao cardápio, verificando se o nome já existe para evitar confusão.
    public boolean adicionarItemCardapio(String nome, BigDecimal preco, int amount) {
        for (MenuItem m : menuItems) {
            if (m.getName   ().equals(nome)) {
                System.out.println("Falha ao adicionar item: Nome de item já existe!");
                return false;
            }
        }
        // Aqui fizemos a criação do item e adicionamos à lista de cardápio.
        MenuItem item = new MenuItem(nome, preco, amount);
        menuItems.add(item);
        System.out.println("Item '" + nome + "' adicionado ao cardápio!");
        return true;
    }

    // Listamos os itens do cardápio mostrando o que temos disponível.
    public void listarItens() {
        if (menuItems.isEmpty()) {
            System.out.println("Nenhum item de cardápio cadastrado.");
            return;
        }
        System.out.println("=== CARDÁPIO ===");
        // Aqui fizemos um loop mostrando cada item com seus detalhes.
        for (MenuItem item : menuItems) {
            System.out.println(item);
        }
    }

    // Registramos um pedido já criado e adicionamos à nossa lista de pedidos.
    public Order registrarPedido(Order order) {
        if (order == null) throw new IllegalArgumentException("Order não pode ser nulo");
        // Aqui fizemos a adição segura do pedido.
        orders.add(order);
        System.out.println("Pedido #" + order.getId() + " registrado com sucesso!");
        return order;
    }

    // Retorna todos os pedidos cadastrados, exibindo mensagem se não houver nenhum
    public List<Order> listarTodosPedidos() {
        if (orders.isEmpty()) {
            System.out.println("Nenhum pedido registrado atualmente.");
            return new ArrayList<>(); // devolvemos uma lista vazia
        }
        return new ArrayList<>(orders); // devolvemos uma cópia para não alterar a lista interna
    }

    // Aqui fizemos a consulta de pedidos filtrando pelo status escolhido
    public List<Order> consultarPedidoPorStatus(OrderStatus statusDesejado) {
        List<Order> resultado = new ArrayList<>();
        for (Order pedido : orders) {
            if (pedido.getStatus() == statusDesejado) {
                resultado.add(pedido);
            }
        }
        if (resultado.isEmpty()) {
            System.out.println("Nenhum pedido encontrado para o status " + statusDesejado);
        }
        return resultado;
    }

    // Geramos um relatório simples: total de pedidos e valor arrecadado.
    public void gerarRelatorioSimplificado() {
        if (orders.isEmpty()) {
            System.out.println("Nenhum pedido registrado ainda. Bora vender!");
            return;
        }
        int totalPedidos = orders.size();
        BigDecimal valorTotal = BigDecimal.ZERO;
        // Aqui fizemos a soma de todos os valores dos pedidos.
        for (Order pedido : orders) {
            valorTotal = valorTotal.add(pedido.total());
        }
        System.out.println("=== RELATÓRIO SIMPLES ===");
        System.out.println("Total de pedidos: " + totalPedidos);
        System.out.println("Valor total arrecadado: " + nf.format(valorTotal));
    }

    // Geramos um relatório detalhado mostrando cada pedido, cliente e itens.
    public void gerarRelatorioDetalhado() {
        if (orders.isEmpty()) {
            System.out.println("Nenhum pedido registrado para detalhar.");
            return;
        }
        System.out.println("=== RELATÓRIO DETALHADO ===");
        // Aqui fizemos a exibição de cada pedido, detalhando cliente, itens e valores.
        for (Order pedido : orders) {
            Customer cliente = pedido.getCustomer();
            System.out.println("Pedido número: " + pedido.getId());
            System.out.printf("Cliente: %s | Telefone: %s | Código: %d%n",
                    cliente.getName(), cliente.getPhoneNumber(), cliente.getId());
            for (OrderItem item : pedido.getItems()) {
                System.out.printf("%s x%d - Subtotal: %s%n",
                        item.getMenuItem().getName(),
                        item.getAmount(),
                        nf.format(item.subtotal()));
            }
            System.out.println("Valor total do pedido: " + nf.format(pedido.total()));
            System.out.println("---------------------------");
        }
    }
}
