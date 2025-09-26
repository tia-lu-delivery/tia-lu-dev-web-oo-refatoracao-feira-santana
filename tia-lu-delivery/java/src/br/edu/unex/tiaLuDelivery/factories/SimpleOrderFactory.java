package br.edu.unex.tiaLuDelivery.factories;

import br.edu.unex.tiaLuDelivery.model.Customer;
import br.edu.unex.tiaLuDelivery.model.Order;

public class SimpleOrderFactory implements OrderFactory{
    @Override
    public Order createOrder(Customer customer) {
        return null;
    }
}
