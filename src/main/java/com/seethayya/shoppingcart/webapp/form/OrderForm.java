package com.seethayya.shoppingcart.webapp.form;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/12/14
 * Time: 8:21 PM
 */
public class OrderForm {


    private Long id;
    private String orderId;
    private BigDecimal totalPrice;
    private Date orderDate;
    private List<OrderDetailForm> orderDetailForms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderDetailForm> getOrderDetailForms() {
        return orderDetailForms;
    }

    public void setOrderDetailForms(List<OrderDetailForm> orderDetailForms) {
        this.orderDetailForms = orderDetailForms;
    }
}
