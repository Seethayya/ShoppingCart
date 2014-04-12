package com.seethayya.shoppingcart.dao;

import com.mysql.jdbc.Driver;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/13/14
 * Time: 12:59 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath*:spring/applicationContext.xml")
public abstract class DataSourceConfigTest {

    @BeforeClass
    public static void setUpDataSource() throws NamingException, SQLException {
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        DataSource dataSource = new SimpleDriverDataSource(new Driver(), "jdbc:mysql://localhost:3306/shoppingCart", "root", "root");
        builder.bind("jdbc/shoppingCart", dataSource);
        builder.activate();
    }

}
