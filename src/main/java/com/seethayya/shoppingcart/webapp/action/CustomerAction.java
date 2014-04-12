package com.seethayya.shoppingcart.webapp.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.seethayya.shoppingcart.dto.Item;
import com.seethayya.shoppingcart.service.CustomerOrderService;
import com.seethayya.shoppingcart.service.ItemService;
import com.seethayya.shoppingcart.util.RandomUtils;
import com.seethayya.shoppingcart.webapp.form.Customer;
import com.seethayya.shoppingcart.service.CustomerService;
import com.seethayya.shoppingcart.webapp.form.OrderDetailForm;
import com.seethayya.shoppingcart.webapp.form.OrderForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/9/14
 * Time: 1:20 PM
 */
public class CustomerAction extends BaseAction {

    private String userName;
    private Customer customer;
    private List<Item> itemList;
    private List<OrderDetailForm> orderDetailForms;
    private List<OrderForm> orderForms;
    private String orderId;
    private CustomerService customerService;
    private CustomerOrderService customerOrderService;
    private ItemService itemService;

    private static final Logger LOGGER = Logger.getLogger(CustomerAction.class);

    public String execute() {
        LOGGER.debug("Started Action HOME");
        customerService.findCustomer(userName);
        return SUCCESS;
    }


    public String signIn() {
        if (validateCustomerLogin()) {
            return ERROR;
        }
        com.seethayya.shoppingcart.dto.Customer customerDto = customerService.findCustomerByEmailId(customer.getEmailId());
        if (!customer.getPassword().equals(customerDto.getPassword())) {
            addActionError("Username and Pass word is in correct");
            return ERROR;
        }
        Map session = ActionContext.getContext().getSession();
        session.put("customer", customerDto);
        loadOrders();
        return SUCCESS;
    }

    private boolean validateCustomerLogin() {
        if (StringUtils.isBlank(customer.getEmailId())) {
            addActionError(getText("userName.empty"));
        } else if (StringUtils.isBlank(customer.getPassword())) {
            addActionError(getText("password.empty"));
        }
        return hasErrors();
    }

    public String signUp() {
        return SUCCESS;
    }

    public String registerCustomer() {
        if (validateCustomer()) {
            return ERROR;
        }
        customerService.saveOrUpdateCustomer(customer);
        loadOrders();
        addActionMessage("Registration done successfully.");
        return SUCCESS;
    }

    private boolean validateCustomer() {
        if (StringUtils.isBlank(customer.getFirstName())) {
            addActionError("First name cannot be empty");
        } else if (StringUtils.isBlank(customer.getLastName())) {
            addActionError("Last name cannot be empty");
        } else if (StringUtils.isBlank(customer.getPassword())) {
            addActionError("Password cannot be empty");
        } else if (StringUtils.isBlank(customer.getEmailId())) {
            addActionError("Email id cannot be empty");
        } else if (customer.getId() == null && customerService.findCustomerByEmailId(customer.getEmailId()) != null) {
            addActionError("Email id already exist");
        } else if (StringUtils.isBlank(customer.getPhoneNo())) {
            addActionError("Mobile no cannot be empty");
        } else if (!StringUtils.isNumeric(customer.getPhoneNo())) {
            addActionError("Enter valid Mobile no");
        } else if (customer.getId() == null && customerService.findCustomerByMobileNo(Long.parseLong(customer.getPhoneNo())) != null) {
            addActionError("Mobile no already exist");
        }
        return hasErrors();
    }

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
        if (sessionCustomer == null) {
        }
        orderForms = customerOrderService.findCustomerOrders(sessionCustomer.getId());
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
        customerOrderService.createOrder(orderId, orderDetailForms, sessionCustomer.getId());
        loadOrders();
        return SUCCESS;
    }

    public String findCustomer() {
        LOGGER.debug("Started Action findCustomer email:" + userName);
        customerService.findCustomerByEmailId(userName);
        return SUCCESS;
    }

    public String findAllCustomers() {
        LOGGER.debug("Started Action find ALl Customer email:");
        customerService.findAllCustomers();
        return SUCCESS;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderForm> getOrderForms() {
        return orderForms;
    }

    public void setOrderForms(List<OrderForm> orderForms) {
        this.orderForms = orderForms;
    }

    @Resource
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Resource
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Resource
    public void setCustomerOrderService(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }
}
