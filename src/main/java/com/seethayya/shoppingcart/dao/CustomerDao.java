package com.seethayya.shoppingcart.dao;


import com.seethayya.shoppingcart.dto.Customer;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/10/14
 * Time: 1:51 PM
 */
public interface CustomerDao  extends GenericDao<Customer, Long> {

    List<Customer> findCustomerByEmail(String emailId);

    List<Customer> findCustomerByMobileNo(Long mobileNo);


}
