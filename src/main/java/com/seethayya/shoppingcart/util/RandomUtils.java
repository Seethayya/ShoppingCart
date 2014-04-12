package com.seethayya.shoppingcart.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Manjunatha
 * Date: 4/12/14
 * Time: 2:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class RandomUtils {

    private static final String RANDOM_STRING_CHARS = "1234567890";

    public static String generateRandomOrderId() {
       return RandomStringUtils.random(8, RANDOM_STRING_CHARS);
    }
}
