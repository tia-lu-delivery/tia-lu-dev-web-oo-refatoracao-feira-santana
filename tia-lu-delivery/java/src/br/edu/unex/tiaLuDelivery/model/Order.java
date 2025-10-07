package br.edu.unex.tiaLuDelivery.model;

import br.edu.unex.tiaLuDelivery.observer.OrderObserver;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class Order {
    private static long sequence = 0L;
    private final long id;
    private final Customer customer;
    private final List<OrderItem> items;
    private OrderStatus status;
    private final CopyOnWriteArrayList<OrderObserver> observers = new CopyOnWriteArrayList<>();

    public Order(Customer customer) {
        this.id = ++sequence;
        this.customer = customer;
        this.items = new ArrayList();
        this.status = OrderStatus.AWAITING_MERCHANT_ACCEPTANCE;
    }

    public void addObserver(OrderObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    private void notifyStatusChange(OrderStatus previous, OrderStatus current) {
        for (OrderObserver o : observers) {
            o.onStatusChanged(this, previous, current);
        }
    }

    public void updateStatus(OrderStatus newStatus) {
        if (newStatus == null || newStatus == this.status) return;
        OrderStatus previous = this.status;
        this.status = newStatus;
        notifyStatusChange(previous, newStatus);
    }

    public void addItem(MenuItem menuItem, int amount) {
        if (menuItem == null) {
            throw new IllegalArgumentException("The selected menu item can not be empty or null");
        } else {
            this.items.add(new OrderItem(this, menuItem, amount));
        }
    }

    public void updateItemAmount(MenuItem menuItem, int amount) {
        this.items.stream().filter((item) -> {
            return item.getMenuItem().equals(menuItem);
        }).findFirst().ifPresent((orderItem) -> {
            orderItem.setAmount(amount);
        });
    }

    public BigDecimal total() {
        return (BigDecimal)this.items.stream().map(OrderItem::subtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public long getId() {
        return this.id;
    }

    public List<OrderItem> getItems() {
        return this.items;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        updateStatus(status);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            Order order = (Order)object;
            return this.id == order.id && Objects.equals(this.items, order.items) && this.status == order.status;
        } else {
            return false;
        }
    }

    public String toString() {
        long var10000 = this.id;
        return "Order{id=" + var10000 + ", items=" + String.valueOf(this.items) + ", status=" + String.valueOf(this.status) + "}";
    }
}

