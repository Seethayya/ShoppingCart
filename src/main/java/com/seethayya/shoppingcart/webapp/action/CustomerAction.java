package com.seethayya.shoppingcart.webapp.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.seethayya.shoppingcart.dto.CustomerOrder;
import com.seethayya.shoppingcart.dto.Item;
import com.seethayya.shoppingcart.dto.OrderDetails;
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
import java.util.ArrayList;
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
    private CustomerService customerService;
    private static final String ALREADY_SIGN_IN =  "alreadySignIn";

    private static final Logger LOGGER = Logger.getLogger(CustomerAction.class);


    public String execute() {

        String s= null;
        if(getCustomerFromSession() != null) {
            return ALREADY_SIGN_IN;
        }
        return SUCCESS;
    }


    public String signIn() {
        if(getCustomerFromSession() != null) {
            return ALREADY_SIGN_IN;
        }
        if (validateCustomerLogin()) {
            return ERROR;
        }
        com.seethayya.shoppingcart.dto.Customer customerDto = customerService.findCustomerByEmailId(customer.getEmailId());
        if(null == customerDto) {
            addActionError("UserName does not exist");
            return ERROR;
        }
        if (!customer.getPassword().equals(customerDto.getPassword())) {
            addActionError("Username and Pass word is in correct");
            return ERROR;
        }
        Map session = ActionContext.getContext().getSession();
        session.put("customer", customerDto);
        //loadOrders();
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
        if(getCustomerFromSession() != null) {
            return ALREADY_SIGN_IN;
        }
        return SUCCESS;
    }

    public String registerCustomer() {
        if(getCustomerFromSession() != null) {
            return ALREADY_SIGN_IN;
        }
        if (validateCustomer()) {
            return ERROR;
        }
        com.seethayya.shoppingcart.dto.Customer customerDto  =customerService.saveOrUpdateCustomer(customer);
        Map session = ActionContext.getContext().getSession();
        session.put("customer", customerDto);
        //loadOrders();
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

    public String signOut() {
        getSessionMap().remove("customer");
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

    @Resource
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}