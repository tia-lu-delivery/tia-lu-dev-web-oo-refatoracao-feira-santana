package br.edu.unex.tiaLuDelivery.services;

import br.edu.unex.tiaLuDelivery.model.Order;

public class RefundService {
    public static void processRefund(Order order) {
        System.out.println("💰 Processando reembolso para pedido " + order.getId() + 
                         " no valor de R$" + order.total());
        System.out.println("...");
        System.out.println("...");
        System.out.println("...");
        System.out.println("✅ Reembolso processado com sucesso");
    }
}
