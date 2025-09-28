package br.edu.unex.tiaLuDelivery.model;

import br.edu.unex.tiaLuDelivery.states.IOrderState;
import br.edu.unex.tiaLuDelivery.states.WaitingAcceptanceState;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

    private static long sequence = 0;

    private final long id;
    private final Customer customer;
    private final List<OrderItem> items;
    private IOrderState state;

    public Order(final Customer customer) {
        this.id = ++sequence;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.state = new WaitingAcceptanceState();
    }

    public void addItem(final MenuItem menuItem, final int amount) {
        if (menuItem == null) {
            throw new IllegalArgumentException("The selected menu item cannot be empty or null");
        }
        items.add(new OrderItem(this, menuItem, amount));
    }

    public void updateItemAmount(final MenuItem menuItem, final int amount) {
        this.items.stream()
                .filter(item -> item.getMenuItem().equals(menuItem))
                .findFirst()
                .ifPresent(orderItem -> orderItem.setAmount(amount));
    }

    public BigDecimal total() {
        return this.items.stream()
                .map(OrderItem::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Customer getCustomer() {
        return customer;
    }

    public long getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void accept() {
        state.accept(this);
    }

    public void reject(String reason) {
        state.reject(this, reason);
    }

    public void startPreparation() {
        state.startPreparation(this);
    }

    public void finishPreparation() {
        state.finishPreparation(this);
    }

    public void sendForDelivery() {
        state.sendForDelivery(this);
    }

    public void confirmDelivery() {
        state.confirmDelivery(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    public String getCurrentStatus() {
        return state.getStatus();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Order order = (Order) object;
        return id == order.id && Objects.equals(items, order.items) && state == order.state;
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", items=" + items
                + ", state=" + state
                + '}';
    }
}
