package com.seethayya.shoppingcart.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/12/14
 * Time: 11:15 PM
 */
public abstract class BaseAction extends ActionSupport implements SessionAware {

    private SessionMap<String,Object> sessionMap;

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    private com.seethayya.shoppingcart.dto.Customer getCustomerFromSession() {
        return (com.seethayya.shoppingcart.dto.Customer) getSessionMap().get("customer");
    }

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap<String, Object>) map;
    }
}
