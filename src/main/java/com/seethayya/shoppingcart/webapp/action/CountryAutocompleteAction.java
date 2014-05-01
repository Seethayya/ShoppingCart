package com.seethayya.shoppingcart.webapp.action;


import com.seethayya.shoppingcart.dao.CountryDao;
import com.seethayya.shoppingcart.dto.Country;
import com.seethayya.shoppingcart.service.CountryService;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/21/14
 * Time: 12:57 AM
 */
public class CountryAutocompleteAction extends BaseAction {

    private List<String> countryList;
    private String country;
    private CountryService countryService;
    private static final Logger LOGGER = Logger.getLogger(CountryAutocompleteAction.class);

    public String execute() {
        LOGGER.debug("-----CountryAutocompleteAction:"+country);
        countryList = new ArrayList<String>();
        for (Country countryDto : countryService.findAllCountries()) {
            if (countryDto.getName().contains(country)) {
                countryList.add(countryDto.getName());
            }
        }
        return SUCCESS;
    }

    public List<String> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<String> countryList) {
        this.countryList = countryList;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Resource
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }
}
