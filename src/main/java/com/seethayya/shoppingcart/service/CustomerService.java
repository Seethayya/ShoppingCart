package com.seethayya.shoppingcart.service;

import com.seethayya.shoppingcart.webapp.form.Customer;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/9/14
 * Time: 1:41 PM
 */
public interface CustomerService {

    com.seethayya.shoppingcart.dto.Customer findCustomerById(Long id);

    Customer saveOrUpdateCustomer(Customer customer);

    Customer findCustomer(String userName);

    com.seethayya.shoppingcart.dto.Customer findCustomerByEmailId(String emailId);

    com.seethayya.shoppingcart.dto.Customer findCustomerByMobileNo(Long mobileNo);

    List<com.seethayya.shoppingcart.dto.Customer> findAllCustomers();
}
