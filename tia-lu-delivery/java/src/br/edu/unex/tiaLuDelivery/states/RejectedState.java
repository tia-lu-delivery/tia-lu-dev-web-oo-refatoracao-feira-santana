package br.edu.unex.tiaLuDelivery.states;

import br.edu.unex.tiaLuDelivery.exceptions.StateInvalidException;
import br.edu.unex.tiaLuDelivery.model.Order;

public class RejectedState  implements IOrderState{

    @Override
    public void accept(Order order) {
        throw new StateInvalidException("O pedido foi rejeitado, não pode ser aceito");
    }

    @Override
    public void reject(Order order, String reason) {
        throw new StateInvalidException("O pedido foi rejeitado");
    }
    
    @Override
    public void cancel(Order order) {
        throw new StateInvalidException("O pedido foi rejeitado, não pode ser cancelado");
    }

    @Override
    public void startPreparation(Order order) {
        throw new StateInvalidException("O pedido foi rejeitado, não pode ser iniciado para preparação");
    }

    @Override
    public void finishPreparation(Order order) {
        throw new StateInvalidException("O pedido foi rejeitado, não pode ser finalizado");
    }

    @Override
    public void confirmDelivery(Order order) {
        throw new StateInvalidException("O pedido foi rejeitado, não pode ser confirmado");
    }

    @Override
    public void sendForDelivery(Order order) {
        throw new StateInvalidException("O pedido foi rejeitado, não pode ser enviado para entrega");
    }

    @Override
    public String getStatus() {
        return "Rejeitado";
    }
    
}
