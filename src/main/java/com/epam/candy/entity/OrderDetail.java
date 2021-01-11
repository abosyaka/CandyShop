package com.epam.candy.entity;

import java.util.Objects;

public class OrderDetail extends Entity{
    private Long id;
    private Order order;
    private Good good;
    private Integer count;

    public OrderDetail(Long id, Order order, Good good, Integer count) {
        this.id = id;
        this.order = order;
        this.good = good;
        this.count = count;
    }

    public OrderDetail(Order order, Good good, Integer count) {
        this.order = order;
        this.good = good;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return order.equals(that.order) &&
                good.equals(that.good) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, good, count);
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", order=" + order +
                ", good=" + good +
                ", count=" + count +
                '}';
    }
}
