package br.edu.unex.tiaLuDelivery.builders;

import br.edu.unex.tiaLuDelivery.model.Customer;
import br.edu.unex.tiaLuDelivery.model.MenuItem;
import br.edu.unex.tiaLuDelivery.model.Order;

// Etapas da construção do Pedido, necessário implementá-las para
// garantir a ordem correta das operações (Cliente, itens, confirmação)
public interface OrderSteps {
    interface CustomerStep {
        ItemsStep setCustomer(Customer customer);
    }
    interface ItemsStep {
        ItemsStep addItem(MenuItem menuItem, int amount);
        ConfirmStep finishItems();
    }
    interface ConfirmStep {
        Order build();
    }
}
