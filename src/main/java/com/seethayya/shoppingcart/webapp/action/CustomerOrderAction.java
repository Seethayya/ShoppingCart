package com.seethayya.shoppingcart.webapp.action;

import com.seethayya.shoppingcart.dto.CustomerOrder;
import com.seethayya.shoppingcart.dto.Item;
import com.seethayya.shoppingcart.dto.OrderDetails;
import com.seethayya.shoppingcart.service.CustomerOrderService;
import com.seethayya.shoppingcart.service.CustomerService;
import com.seethayya.shoppingcart.service.ItemService;
import com.seethayya.shoppingcart.util.RandomUtils;
import com.seethayya.shoppingcart.webapp.form.OrderDetailForm;
import com.seethayya.shoppingcart.webapp.form.OrderForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/13/14
 * Time: 3:18 PM
 */
public class CustomerOrderAction extends BaseAction {

    private static final Logger LOGGER = Logger.getLogger(CustomerOrderAction.class);

    private List<Item> itemList;
    private List<OrderDetailForm> orderDetailForms;
    private List<OrderForm> orderForms;
    private OrderForm orderForm;
    private String orderId;
    private CustomerOrderService customerOrderService;
    private ItemService itemService;


    public String orderDetails() {
        return SUCCESS;
    }

    public String newOrder() {
        boolean isOrderIdNotExist = false;
        do {
            orderId = RandomUtils.generateRandomOrderId();
            isOrderIdNotExist = customerOrderService.findOrdersByOrderId(orderId).isEmpty();
        } while (!isOrderIdNotExist);
        itemList = itemService.loadAllItems();
        return SUCCESS;
    }

    public String loadOrders() {
        com.seethayya.shoppingcart.dto.Customer sessionCustomer = getCustomerFromSession();
        if (sessionCustomer != null) {
            orderForms = customerOrderService.findCustomerOrders(sessionCustomer.getId());
        }
        return SUCCESS;
    }

    public String signOut() {
        getSessionMap().remove("customer");
        return SUCCESS;
    }

    private com.seethayya.shoppingcart.dto.Customer getCustomerFromSession() {
        return (com.seethayya.shoppingcart.dto.Customer) getSessionMap().get("customer");
    }


    public String createOrder() {
        com.seethayya.shoppingcart.dto.Customer sessionCustomer = getCustomerFromSession();
        if (sessionCustomer == null) {
        }
        LOGGER.debug("---OrderId:" + orderId);
        LOGGER.debug("---Order list:" + orderDetailForms);
        customerOrderService.createOrUpdateOrder(orderId, orderDetailForms, sessionCustomer.getId());
        loadOrders();
        return SUCCESS;
    }

    public String existingOrder() {
        LOGGER.debug("-------ExistingOrder------Order Id:" + orderId);
        if (StringUtils.isBlank(orderId)) {
            addActionError("Please select order to edit");
            loadOrders();
            return ERROR;
        }
        List<CustomerOrder> customerOrders = customerOrderService.findOrdersByOrderId(orderId);
        orderForm = new OrderForm();
        CustomerOrder customerOrder = customerOrders.get(0);
        orderForm.setOrderId(customerOrder.getOrderId());
        orderForm.setId(customerOrder.getId());
        List<OrderDetailForm> orderDetailForms = new ArrayList<OrderDetailForm>();
        for (OrderDetails orderDetails : customerOrder.getOrderDetailses()) {
            OrderDetailForm orderDetailForm = new OrderDetailForm();
            orderDetailForm.setId(orderDetails.getId());
            orderDetailForm.setItemId(orderDetails.getItem().getId());
            orderDetailForm.setQuantity(orderDetails.getQuantity());
            orderDetailForms.add(orderDetailForm);
        }
        orderForm.setOrderDetailForms(orderDetailForms);
        itemList = itemService.loadAllItems();
        return SUCCESS;
    }

    public String updateOrder() {
        LOGGER.debug("-------orderId:" + orderId);
        LOGGER.debug("-------orderId:orderDetailForms" + orderDetailForms);
        com.seethayya.shoppingcart.dto.Customer sessionCustomer = getCustomerFromSession();
        customerOrderService.createOrUpdateOrder(orderId, orderDetailForms, sessionCustomer.getId());
        loadOrders();
        return SUCCESS;
    }

    public String deleteOrder() {
        LOGGER.debug("-----Delete--OrderForm:" + orderForm.getId());
        customerOrderService.deleteOrder(orderForm.getId());
        loadOrders();
        return SUCCESS;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<OrderDetailForm> getOrderDetailForms() {
        return orderDetailForms;
    }

    public void setOrderDetailForms(List<OrderDetailForm> orderDetailForms) {
        this.orderDetailForms = orderDetailForms;
    }

    public List<OrderForm> getOrderForms() {
        return orderForms;
    }

    public void setOrderForms(List<OrderForm> orderForms) {
        this.orderForms = orderForms;
    }

    public OrderForm getOrderForm() {
        return orderForm;
    }

    public void setOrderForm(OrderForm orderForm) {
        this.orderForm = orderForm;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Resource
    public void setCustomerOrderService(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @Resource
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
}
