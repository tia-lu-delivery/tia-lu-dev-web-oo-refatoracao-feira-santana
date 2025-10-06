package br.edu.unex.tiaLuDelivery.states;

import br.edu.unex.tiaLuDelivery.exceptions.StateInvalidException;
import br.edu.unex.tiaLuDelivery.model.Order;
import br.edu.unex.tiaLuDelivery.services.NotificationService;

public class WaitingAcceptanceState implements IOrderState {

    @Override
    public void accept(Order order) {
        System.out.println("Pedido" + order.getId() + " aceito com sucesso");
        order.setState(new AcceptedState());
        NotificationService.notificationCustomer(order.getCustomer(),
                "Seu pedido foi aceito");
    }

    @Override
    public void reject(Order order, String reason) {
        System.out.println("Pedido" + order.getId() + " rejeitado, motivo: " + reason);
        order.setState(new RejectedState());
        NotificationService.notificationCustomer(order.getCustomer(),
                "Seu pedido foi rejeitado, motivo: " + reason);
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
        throw new StateInvalidException("O pedido ainda não foi aceito");
    }

    @Override
    public void finishPreparation(Order order) {
        throw new StateInvalidException("O pedido ainda não foi aceito");
    }

    @Override
    public void sendForDelivery(Order order) {
        throw new StateInvalidException("O pedido ainda não foi aceito");
    }

    @Override
    public void confirmDelivery(Order order) {
        throw new StateInvalidException("O pedido ainda não foi aceito");
    }

    @Override
    public String getStatus() {
        return "Aguardando o Estabelecimento Aceitar";
    }
}
