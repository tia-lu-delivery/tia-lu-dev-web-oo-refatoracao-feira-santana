package br.edu.unex.tiaLuDelivery.states;

import br.edu.unex.tiaLuDelivery.model.Order;

public class WaitingAcceptanceState implements IOrderState {
    @Override
    public void accept(Order order) {

    }
    @Override
    public void reject(Order order, String reason) {

    }
    @Override
    public void startPreparation(Order order) {

    }
    @Override
    public void finishPreparation(Order order) {

    }
    @Override
    public void sendForDelivery(Order order) {

    }
    @Override
    public void confirmDelivery(Order order) {

    }
    @Override
    public void cancel(Order order) {

    }

    @Override
    public String getStatus() {
        return "Aguardando Aceite do Estabelecimento";
    }
}
