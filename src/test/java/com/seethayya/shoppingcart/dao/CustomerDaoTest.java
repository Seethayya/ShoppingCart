package com.seethayya.shoppingcart.dao;

import com.mysql.jdbc.Driver;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * User: Seethayya
 * Date: 4/13/14
 * Time: 12:25 AM
 */
public class CustomerDaoTest extends DataSourceConfigTest {

    private CustomerDao customerDao;

    @Test
    @Transactional(value = "shoppingCartTransactionManager", propagation = Propagation.REQUIRED)
    public void testCustomer() {
        System.out.println(customerDao.read(1l));
    }

    @Resource
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}
