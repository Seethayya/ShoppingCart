package com.seethayya.shoppingcart;

import com.seethayya.shoppingcart.service.CustomerService;
import com.seethayya.shoppingcart.webapp.form.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/9/14
 * Time: 12:55 PM
 */
public class Test {

    /**
     * TODO::Enable the property setter datasource in LocalContainerEntityManagerFactoryBean before running this main method
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("************** BEGINNING PROGRAM **************");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        CustomerService customerService = (CustomerService) context.getBean("customerService");
        Customer customer = new Customer();
        customer.setFirstName("Alba");
        customer.setLastName("Alba");
        customer.setEmailId("alba@gmail.com");
        customer.setPhoneNo("986657777");
        customer.setPassword("986657777");
        customerService.saveOrUpdateCustomer(customer);
        System.out.println("Person : " + customer + " added successfully");
        System.out.println("The list of all persons = " + customer);
        System.out.println("************** ENDING PROGRAM *****************");
    }
  }
