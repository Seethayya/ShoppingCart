package com.seethayya.shoppingcart.webapp.form;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/12/14
 * Time: 1:23 PM
 */
public class OrderDetailForm {

    private Long itemId;
    private Long quantity;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetailForm{" +
                "itemId=" + itemId +
                ", quantity=" + quantity +
                '}';
    }
}
