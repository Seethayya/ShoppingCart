package com.seethayya.shoppingcart.mail;

/**
 * User: Anish
 * Date: 4/26/14
 * Time: 10:08 PM
 */
public interface VelocityTemplate {

    /**
     *
     * @param vmFileLocation
     * @param data
     * @return
     */
    public String prepareStringFromVelocityTemplate(String vmFileLocation, Object... data);

}
