package br.edu.unex.tiaLuDelivery.observer;

import br.edu.unex.tiaLuDelivery.model.Order;
import br.edu.unex.tiaLuDelivery.model.OrderStatus;

public interface OrderObserver {
    void onStatusChanged(Order order, OrderStatus previous, OrderStatus current);
}

