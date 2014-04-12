package com.seethayya.shoppingcart.dto;

import com.seethayya.shoppingcart.enums.OrderType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/10/14
 * Time: 2:55 PM
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "orders")
@NamedQueries(
        {@NamedQuery(name="CustomerOrder.findOrderByOrderId", query = "from CustomerOrder order where order.orderId = ?")}
)
public class CustomerOrder extends BaseDto {

    private OrderType orderType;
    private Date orderDate;
    private String orderId;
    private BigDecimal totalPrice;
    private Customer customer;
    private Set<OrderDetails> orderDetailses;

    @Column(name = "ORDER_ID")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Column(name = "order_type")
    @Type(type = "com.seethayya.shoppingcart.util.GenericEnumUserType", parameters = {
            @Parameter(name = "enumClass", value = "com.seethayya.shoppingcart.enums.OrderType"),
            @Parameter(name = "identifierMethod", value = "name"),
            @Parameter(name = "valueOfMethod", value = "valueOf") })
    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP )
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Column(name = "TOTAL_PRICE")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customerOrder")
    public Set<OrderDetails> getOrderDetailses() {
        return orderDetailses;
    }

    public void setOrderDetailses(Set<OrderDetails> orderDetailses) {
        this.orderDetailses = orderDetailses;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "orderType=" + orderType +
                ", orderId='" + orderId + '\'' +
                ", orderDetailses='"+orderDetailses + "\'" +
                '}';
    }
}
