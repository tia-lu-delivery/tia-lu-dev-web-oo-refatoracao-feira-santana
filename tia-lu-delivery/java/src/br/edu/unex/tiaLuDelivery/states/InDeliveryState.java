package br.edu.unex.tiaLuDelivery.states;

import br.edu.unex.tiaLuDelivery.exceptions.StateInvalidException;
import br.edu.unex.tiaLuDelivery.model.Order;

public class InDeliveryState implements IOrderState {
    @Override
    public void cancel(Order order) {
        throw new StateInvalidException("O pedido já foi enviado para entrega, não pode ser cancelado");
    }

    @Override
    public void reject(Order order, String reason) {
        throw new StateInvalidException("O pedido já foi enviado para entrega, não pode ser rejeitado");
    }

    @Override
    public void accept(Order order) {
        throw new StateInvalidException("O pedido já foi enviado para entrega, não pode ser aceito");
    }

    @Override
    public void startPreparation(Order order) {
        throw new StateInvalidException("O pedido já foi enviado para entrega, não pode ser iniciado para preparação");
    }

    @Override
    public void finishPreparation(Order order) {
        throw new StateInvalidException("O pedido já foi enviado para entrega, não pode ser finalizado na preparação");
    }

    @Override
    public void confirmDelivery(Order order) {
        throw new StateInvalidException("O pedido já foi enviado para entrega, não pode ser confirmado");
    }

    @Override
    public void sendForDelivery(Order order) {
        throw new StateInvalidException("O pedido já foi enviado para entrega, não pode ser enviado para entrega");
    }
    @Override
    public String getStatus() {
        return "Em entrega";
    }
}
