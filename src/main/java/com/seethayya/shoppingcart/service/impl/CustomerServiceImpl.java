package com.seethayya.shoppingcart.service.impl;

import com.seethayya.shoppingcart.dao.CustomerDao;
import com.seethayya.shoppingcart.webapp.form.Customer;
import com.seethayya.shoppingcart.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/9/14
 * Time: 1:41 PM
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;

    public com.seethayya.shoppingcart.dto.Customer findCustomerById(Long id) {
        return customerDao.read(id);
    }

    @Transactional(value = "shoppingCartTransactionManager", propagation = Propagation.REQUIRED)
    public com.seethayya.shoppingcart.dto.Customer saveOrUpdateCustomer(Customer customer) {
        com.seethayya.shoppingcart.dto.Customer customerDto = null;
        if (customer.getId() != null) {
            customerDto = customerDao.read(customer.getId());
        } else {
            customerDto = new com.seethayya.shoppingcart.dto.Customer();
        }
        BeanUtils.copyProperties(customer, customerDto);
        customerDto.setMobileNo(Long.parseLong(customer.getPhoneNo()));
        if (customer.getId() == null) {
            customerDao.create(customerDto);
            customer.setId(customerDto.getId());
        } else {
            customerDao.update(customerDto);
        }
        return customerDto;
    }

    @Transactional(value = "shoppingCartTransactionManager", propagation = Propagation.REQUIRED)
    public Customer findCustomer(String userName) {
        System.out.println("------findCustomer:" + userName);
        Customer customer = new Customer();
        customer.setPassword("`");
        com.seethayya.shoppingcart.dto.Customer customerDto = new com.seethayya.shoppingcart.dto.Customer();
        customerDto.setEmailId("nsitayya@gmail.com");
        customerDto.setFirstName(userName);
        customerDto.setLastName("Nidadavolu");
        customerDto.setPassword("1234");
        customerDao.create(customerDto);
        return customer;
    }

    public com.seethayya.shoppingcart.dto.Customer findCustomerByEmailId(String emailId) {
        System.out.println("--------EmailId:" + emailId);
        List<com.seethayya.shoppingcart.dto.Customer> customerList = customerDao.findCustomerByEmail(emailId);
        if (customerList != null && customerList.size() > 0) {
            System.out.println("--------customerList:" + customerList.size());
            System.out.println("--------customerList:" + customerList.get(0));
            return customerList.get(0);
        }
        return null;
    }

    public com.seethayya.shoppingcart.dto.Customer findCustomerByMobileNo(Long mobileNo) {
        System.out.println("--------findCustomerByMobileNo:" + mobileNo);
        List<com.seethayya.shoppingcart.dto.Customer> customerList = customerDao.findCustomerByMobileNo(mobileNo);
        if (customerList != null && customerList.size() > 0) {
            System.out.println("--------customerList:" + customerList.size());
            System.out.println("--------customerList:" + customerList.get(0));
            return customerList.get(0);
        }
        return null;
    }

    public List<com.seethayya.shoppingcart.dto.Customer> findAllCustomers() {
        System.out.println("--------load all customers");
        List<com.seethayya.shoppingcart.dto.Customer> customerList = customerDao.loadAll();
        if (customerList != null && customerList.size() > 0) {
            System.out.println("--------customerList:" + customerList.size());
            System.out.println("--------customerList:" + customerList.get(0));
            return customerList;
        }
        return null;
    }


    @Resource
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}
