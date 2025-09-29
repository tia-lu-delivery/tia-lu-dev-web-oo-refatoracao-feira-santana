package br.edu.unex.tiaLuDelivery.services;

import br.edu.unex.tiaLuDelivery.model.Customer;

public class NotificationService {
    
    public static void notificationCustomer(Customer custumer, String mensagem) {
        System.out.println("👤 Notificação para o cliente " + custumer.getName() + ": " + mensagem);
    }
}