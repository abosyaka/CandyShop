package com.epam.candy.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Order extends Entity{
    private Long id;
    private User user;
    private Status status;
    private LocalDate orderDate;

    public Order(Long id, User user, Status status, LocalDate orderDate) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.orderDate = orderDate;
    }

    public Order(User user, Status status, LocalDate orderDate) {
        this.user = user;
        this.status = status;
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return user.equals(order.user) &&
                status.equals(order.status) &&
                orderDate.equals(order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, status, orderDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", status=" + status +
                ", orderDate=" + orderDate +
                '}';
    }
}
