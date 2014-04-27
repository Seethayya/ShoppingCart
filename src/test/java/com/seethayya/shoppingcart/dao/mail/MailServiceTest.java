package com.seethayya.shoppingcart.dao.mail;

import com.seethayya.shoppingcart.dao.CustomerDao;
import com.seethayya.shoppingcart.dao.DataSourceConfigTest;
import com.seethayya.shoppingcart.enums.TemplateNames;
import com.seethayya.shoppingcart.mail.VelocityTemplate;
import org.junit.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: Anish
 * Date: 4/26/14
 * Time: 11:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class MailServiceTest  extends DataSourceConfigTest {

    private CustomerDao customerDao;
    private VelocityTemplate velocityTemplate;

    @Test
    @Transactional(value = "shoppingCartTransactionManager", propagation = Propagation.REQUIRED)
    public void testCustomer() {
        System.out.println(customerDao.read(1l));
        String text = velocityTemplate.prepareStringFromVelocityTemplate( TemplateNames.templates.getPath()+TemplateNames.registrationMail.name()+".vm", customerDao.read(1l));

        System.out.println("test:"+text);
    }

    @Resource
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Resource
    public void setVelocityTemplate(VelocityTemplate velocityTemplate) {
        this.velocityTemplate = velocityTemplate;
    }
}
