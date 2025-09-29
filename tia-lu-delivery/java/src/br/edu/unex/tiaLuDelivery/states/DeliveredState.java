package br.edu.unex.tiaLuDelivery.states;

import br.edu.unex.tiaLuDelivery.exceptions.StateInvalidException;
import br.edu.unex.tiaLuDelivery.model.Order;

public class DeliveredState implements IOrderState{

    @Override
    public void accept(Order order) {
        throw new StateInvalidException("O pedido ja foi entregue");
    }

    @Override
    public void reject(Order order, String reason) {
        throw new StateInvalidException("O pedido ja foi entregue");
    }

    @Override
    public void cancel(Order order) {
        throw new StateInvalidException("O pedido ja foi entregue");
    }

    @Override
    public void startPreparation(Order order) {
        throw new StateInvalidException("O pedido ja foi entregue");
    }

    @Override
    public void finishPreparation(Order order) {
        throw new StateInvalidException("O pedido ja foi entregue");
    }

    @Override
    public void sendForDelivery(Order order) {
        throw new StateInvalidException("O pedido ja foi entregue");
    }

    @Override
    public void confirmDelivery(Order order) {
        throw new StateInvalidException("O pedido ja foi entregue");
    }

    @Override
    public String getStatus() {
        return "Entregue";
    }
    
}
