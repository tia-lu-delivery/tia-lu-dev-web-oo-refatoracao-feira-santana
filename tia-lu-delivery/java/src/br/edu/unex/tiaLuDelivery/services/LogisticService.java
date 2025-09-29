package br.edu.unex.tiaLuDelivery.services;

import br.edu.unex.tiaLuDelivery.model.Order;

public class LogisticService {
    public static boolean requestCourier (Order order) {
        System.out.println("🚚 Solicitando entregador para pedido " + order.getId() + "...");  
        return true;
    }
}
