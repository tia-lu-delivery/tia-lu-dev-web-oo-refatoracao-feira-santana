package br.edu.unex.tiaLuDelivery.states;

import br.edu.unex.tiaLuDelivery.model.Order;

public interface IOrderState {
    void accept(Order order);
    void reject(Order order, String reason);
    void startPreparation(Order order);
    void finishPreparation(Order order);
    void sendForDelivery(Order order);
    void confirmDelivery(Order order);
    void cancel(Order order);
    String getStatus();
}
