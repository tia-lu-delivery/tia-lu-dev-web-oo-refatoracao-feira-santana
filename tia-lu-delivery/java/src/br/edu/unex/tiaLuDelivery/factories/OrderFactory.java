package br.edu.unex.tiaLuDelivery.factories;

import br.edu.unex.tiaLuDelivery.model.Customer;
import br.edu.unex.tiaLuDelivery.model.Order;

public interface OrderFactory {
    Order createOrder(Customer customer);
}
