package com.beans;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author Xjm
 * @date 2021/6/23   22:53
 */
@Component
public class OrderItem {
    private Integer id;
    private String name;
    private BigDecimal price;
    private BigDecimal totalMoney;
    private Integer count;
    private String orderId;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", totalMoney=" + totalMoney +
                ", count=" + count +
                ", orderId='" + orderId + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderItem() {
    }

    public OrderItem(Integer id, String name, BigDecimal price, BigDecimal totalMoney, Integer count, String orderId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.totalMoney = totalMoney;
        this.count = count;
        this.orderId = orderId;
    }
}
