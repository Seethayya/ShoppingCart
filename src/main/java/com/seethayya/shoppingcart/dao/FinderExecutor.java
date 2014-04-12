package com.seethayya.shoppingcart.dao;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Manjunatha
 * Date: 4/10/14
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FinderExecutor {

     List executeFinder(Method method, final Object[] queryArgs);
}
