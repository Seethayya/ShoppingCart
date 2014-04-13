package com.seethayya.shoppingcart.aop;

import com.seethayya.shoppingcart.dao.FinderExecutor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/10/14
 * Time: 1:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class FinderIntroductionInterceptor implements IntroductionInterceptor {

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        FinderExecutor genericDao = (FinderExecutor) methodInvocation.getThis();

        String methodName = methodInvocation.getMethod().getName();
        if (methodName.startsWith("find")) {
            Object[] arguments = methodInvocation.getArguments();
            return genericDao.executeFinder(methodInvocation.getMethod(), arguments);
        } else {
            return methodInvocation.proceed();
        }
    }

    public boolean implementsInterface(Class intf) {
        return intf.isInterface() && FinderExecutor.class.isAssignableFrom(intf);
    }
}
