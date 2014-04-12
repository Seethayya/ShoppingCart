package com.seethayya.shoppingcart.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Manjunatha
 * Date: 4/12/14
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "item")
public class Item extends BaseDto {

    private String name;
    private BigDecimal price;
    private Set<OrderDetails> orderDetailses;

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "PRICE")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @OneToMany(mappedBy = "item")
    public Set<OrderDetails> getOrderDetailses() {
        return orderDetailses;
    }

    public void setOrderDetailses(Set<OrderDetails> orderDetailses) {
        this.orderDetailses = orderDetailses;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
