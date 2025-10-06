package br.edu.unex.tiaLuDelivery.states;

import br.edu.unex.tiaLuDelivery.exceptions.StateInvalidException;
import br.edu.unex.tiaLuDelivery.model.Order;

public class CanceledState implements IOrderState {

    @Override
    public void accept(Order order) {
        throw new StateInvalidException("O pedido foi cancelado, não pode ser aceito");
    }

    @Override
    public void reject(Order order, String reason) {
        throw new StateInvalidException("O pedido foi cancelado, não pode ser rejeitado");
    }

    @Override
    public void cancel(Order order) {
        throw new StateInvalidException("O pedido ja foi cancelado");
    }

    @Override
    public void startPreparation(Order order) {
        throw new StateInvalidException("O pedido foi cancelado, não pode ser iniciado para preparação");
    }

    @Override
    public void finishPreparation(Order order) {
        throw new StateInvalidException("O pedido foi cancelado, não pode ser finalizado para entrega");
    }

    @Override
    public void confirmDelivery(Order order) {
        throw new StateInvalidException("O pedido foi cancelado, não pode ser confirmado");
    }

    @Override
    public void sendForDelivery(Order order) {
        throw new StateInvalidException("O pedido foi cancelado, não pode ser enviado para entrega");
    }

    @Override
    public String getStatus() {
        return "Cancelado";
    }
    
}
