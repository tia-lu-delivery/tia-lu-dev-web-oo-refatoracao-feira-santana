package br.edu.unex.tiaLuDelivery.builders;

import java.util.ArrayList;
import java.util.List;

import br.edu.unex.tiaLuDelivery.builders.OrderSteps.ConfirmStep;
import br.edu.unex.tiaLuDelivery.builders.OrderSteps.CustomerStep;
import br.edu.unex.tiaLuDelivery.builders.OrderSteps.ItemsStep;
import br.edu.unex.tiaLuDelivery.model.Customer;
import br.edu.unex.tiaLuDelivery.model.MenuItem;
import br.edu.unex.tiaLuDelivery.model.Order;
import br.edu.unex.tiaLuDelivery.model.OrderItem;
import br.edu.unex.tiaLuDelivery.model.OrderStatus;

public class OrderBuilder implements
        // Etapas da construção do Pedido, necessário implementá-las para
        // garantir a ordem correta das operações (Cliente, itens, confirmação)
        OrderSteps.CustomerStep, 
        OrderSteps.ItemsStep, 
        OrderSteps.ConfirmStep {

    private Customer customer;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderBuilder() {}

    // Inicia a construção do Pedido, retornando a primeira etapa
    public static CustomerStep newOrder() {
        return new OrderBuilder();
    }

    // Primeira etapa, retornando a etapa de adição de itens
    @Override
    public ItemsStep setCustomer(Customer customer) {
        if (customer == null)
            throw new IllegalArgumentException("Cliente não pode ser nulo!");
        this.customer = customer;
        return this;
    }

    // Segunda etapa, retornando a mesma etapa para permitir múltiplas adições
    @Override
    public ItemsStep addItem(MenuItem menuItem, int amount) {
        if (menuItem == null)
            throw new IllegalArgumentException("Item não pode ser nulo");
        if (amount <= 0)
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        if (amount > menuItem.getAmount())
            throw new IllegalArgumentException("Quantidade insuficiente no estoque para o item: " + menuItem.getName());

        // Valor nulo para Order por enquanto, só precisamos do MenuItem e quantidade
        items.add(new OrderItem(null, menuItem, amount));
        return this;
    }

    // Finaliza a adição de itens, retornando a etapa de confirmação
    @Override
    public ConfirmStep finishItems() {
        if (items.isEmpty())
            throw new IllegalStateException("Pelo menos um item deve ser adicionado ao pedido");
        return this;
    }

    // Última etapa, retornando o Pedido construído. Essa etapa só pode ser
    // chamada após todas as anteriores terem sido concluídas, evitando erros.
    @Override
    public Order build() {
        Order order = new Order(customer);
        for (OrderItem item : items) {
            // Formamos cada item do pedido novamente, dessa vez dentro do Order
            order.addItem(item.getMenuItem(), item.getAmount());
        }
        order.setStatus(OrderStatus.ACCEPTED);
        return order;
    }
}

/* 

 * Exemplo de uso:
 * Order minhaOrder = OrderBuilder.newOrder()
 *    .setCustomer(meuCustomer)
 *    .addItem(menuItem1, 2)
 *    .addItem(menuItem2, 1)  * Possível adicionar 1 ou mais itens
 *    .finishItems()          * Finaliza a adição de itens
 *    .build();               * Constrói o Pedido final
 
 */