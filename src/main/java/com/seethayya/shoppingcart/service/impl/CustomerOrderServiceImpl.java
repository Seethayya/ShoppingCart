package com.seethayya.shoppingcart.service.impl;

import com.seethayya.shoppingcart.dao.CustomerDao;
import com.seethayya.shoppingcart.dao.CustomerOrderDao;
import com.seethayya.shoppingcart.dao.ItemDao;
import com.seethayya.shoppingcart.dto.Customer;
import com.seethayya.shoppingcart.dto.CustomerOrder;
import com.seethayya.shoppingcart.dto.OrderDetails;
import com.seethayya.shoppingcart.enums.OrderType;
import com.seethayya.shoppingcart.service.CustomerOrderService;
import com.seethayya.shoppingcart.webapp.form.OrderDetailForm;
import com.seethayya.shoppingcart.webapp.form.OrderForm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/12/14
 * Time: 2:29 PM
 */
@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private CustomerOrderDao customerOrderDao;
    private ItemDao itemDao;
    private CustomerDao customerDao;

    public List<OrderForm> findCustomerOrders(Long customerId) {
        List<OrderForm> orderForms = new ArrayList<OrderForm>();
        Customer customer = customerDao.read(customerId);
        if (customer != null && customer.getCustomerOrders() != null && !customer.getCustomerOrders().isEmpty()) {
            for (CustomerOrder customerOrder : customer.getCustomerOrders()) {
                OrderForm orderForm = new OrderForm();
                BeanUtils.copyProperties(customerOrder, orderForm);
                orderForms.add(orderForm);
            }
        }
        return orderForms;
    }

    @Transactional(value = "shoppingCartTransactionManager", propagation = Propagation.REQUIRED)
    public void createOrder(String orderId, List<OrderDetailForm> orderFormList, Long customerId) {

        Customer customer = customerDao.read(customerId);
        CustomerOrder customerOrder = null;
        List<CustomerOrder> customerOrderList = customerOrderDao.findOrderByOrderId(orderId);
        if (customerOrderList != null && !customerOrderList.isEmpty()) {
            customerOrder = customerOrderList.get(0);
        } else {
            customerOrder = new CustomerOrder();
            customerOrder.setOrderDate(new Date());
            customerOrder.setOrderId(orderId);
            customerOrder.setOrderType(OrderType.ONLINE);
        }
        if (customerOrder.getOrderDetailses() == null) {
            customerOrder.setOrderDetailses(new HashSet<OrderDetails>());
        }
        Set<OrderDetails> orderDetailsSet = new LinkedHashSet<OrderDetails>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderDetailForm orderForm : orderFormList) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setItem(itemDao.read(orderForm.getItemId()));
            orderDetails.setQuantity(orderForm.getQuantity());
            orderDetails.setCustomerOrder(customerOrder);
            orderDetailsSet.add(orderDetails);
            totalPrice = totalPrice.add(orderDetails.getItem().getPrice().multiply(BigDecimal.valueOf(orderDetails.getQuantity())));
        }
        customerOrder.setTotalPrice(totalPrice);
        customerOrder.getOrderDetailses().addAll(orderDetailsSet);
        customerOrder.setCustomer(customer);
        if (customerOrder.getId() == null) {
            customerOrderDao.create(customerOrder);
        } else {
            customerOrderDao.update(customerOrder);
        }
    }

    public List<CustomerOrder> findOrdersByOrderId(String orderId) {
        return customerOrderDao.findOrderByOrderId(orderId);
    }

    @Resource
    public void setCustomerOrderDao(CustomerOrderDao customerOrderDao) {
        this.customerOrderDao = customerOrderDao;
    }

    @Resource
    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Resource
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}
