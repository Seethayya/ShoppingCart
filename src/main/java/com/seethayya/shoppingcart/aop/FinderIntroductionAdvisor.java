package com.seethayya.shoppingcart.aop;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

/**
 * Created by IntelliJ IDEA.
 * User: Manjunatha
 * Date: 4/10/14
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class FinderIntroductionAdvisor extends DefaultIntroductionAdvisor {
    public FinderIntroductionAdvisor() {
        super(new FinderIntroductionInterceptor());
    }
}