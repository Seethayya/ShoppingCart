package com.seethayya.shoppingcart.dao;

import com.seethayya.shoppingcart.dto.CustomerOrder;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/12/14
 * Time: 2:25 PM
 */
public interface CustomerOrderDao extends GenericDao<CustomerOrder, Long> {

    List<CustomerOrder> findOrderByOrderId(String orderId);
}
