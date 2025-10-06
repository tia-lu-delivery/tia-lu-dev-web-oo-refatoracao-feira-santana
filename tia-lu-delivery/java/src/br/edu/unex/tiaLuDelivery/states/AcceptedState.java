package br.edu.unex.tiaLuDelivery.states;

import br.edu.unex.tiaLuDelivery.exceptions.StateInvalidException;
import br.edu.unex.tiaLuDelivery.model.Order;
import br.edu.unex.tiaLuDelivery.services.NotificationService;

public class AcceptedState implements IOrderState {

    @Override
    public void accept(Order order) {
        throw new StateInvalidException("O pedido já foi aceito");
    }

    @Override
    public void reject(Order order, String reason) {
        throw new StateInvalidException("O pedido já foi aceito");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Pedido" + order.getId() + " cancelado pelo cliente");
        order.setState(new CanceledState());
        NotificationService.notificationCustomer(order.getCustomer(),
                "Seu pedido foi cancelado");
    }

    @Override
    public void startPreparation(Order order) {
        System.out.println("Pedido" + order.getId() + " iniciado para preparação");
        order.setState(new PreparingState());
        NotificationService.notificationCustomer(order.getCustomer(),
                "Seu pedido foi iniciado para preparação");
    }

    @Override
    public void finishPreparation(Order order) {
        throw new StateInvalidException("Não é possivel finalizar o pedido antes de iniciar a preparação");
    }

    @Override
    public void confirmDelivery(Order order) {
        throw new StateInvalidException("Não é possivel confirmar o pedido antes de iniciar a preparação");
    }

    @Override
    public void sendForDelivery(Order order) {
        throw new StateInvalidException("Não é possivel enviar o pedido antes de iniciar a preparação");
    }

    @Override
    public String getStatus() {
        return "Aceito";
    }
    
}
