package br.edu.unex.tiaLuDelivery.observer;

import br.edu.unex.tiaLuDelivery.model.Order;
import br.edu.unex.tiaLuDelivery.model.OrderStatus;

import java.util.LinkedList;
import java.util.Queue;

public class FilaEntrega implements OrderObserver {

    private final Queue<Order> fila = new LinkedList<>();

    @Override
    public void onStatusChanged(Order order, OrderStatus previous, OrderStatus current) {
        if (current == OrderStatus.WAITING_FOR_COURIER) {
            fila.add(order);
            System.out.printf("[FilaEntrega] Pedido #%d adicionado à fila. Tamanho: %d%n",
                    order.getId(), fila.size());
        }
    }

    public Order proximo() { return fila.poll(); }

    public int tamanho() { return fila.size(); }
}