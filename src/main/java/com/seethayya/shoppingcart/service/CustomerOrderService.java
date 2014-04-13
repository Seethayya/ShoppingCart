package com.seethayya.shoppingcart.service;

import com.seethayya.shoppingcart.dto.CustomerOrder;
import com.seethayya.shoppingcart.webapp.form.OrderDetailForm;
import com.seethayya.shoppingcart.webapp.form.OrderForm;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/12/14
 * Time: 2:29 PM
 */
public interface CustomerOrderService {

    List<OrderForm> findCustomerOrders(Long customerId);

    void createOrUpdateOrder(String orderId, List<OrderDetailForm> orderFormList, Long customerId);

    List<CustomerOrder> findOrdersByOrderId(String orderId);

    void deleteOrder(Long orderId);
}
