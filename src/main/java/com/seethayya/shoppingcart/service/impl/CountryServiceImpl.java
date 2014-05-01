package com.seethayya.shoppingcart.service.impl;

import com.seethayya.shoppingcart.dao.CountryDao;
import com.seethayya.shoppingcart.dto.Country;
import com.seethayya.shoppingcart.service.CountryService;
import org.apache.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Seethayya
 * Date: 4/21/14
 * Time: 3:16 AM
 */
@Service
public class CountryServiceImpl implements CountryService{

    private CountryDao countryDao;
    private List<Country> countryList;
    private static final Logger LOGGER = Logger.getLogger(CountryServiceImpl.class);

    @PostConstruct
    public void loadAllCountries() {
        countryList = countryDao.loadAll();
        LOGGER.debug("Country list from DB:"+countryList.size());


    }

    @Override
    @Cacheable(value = "defaultCache", key = "'countryList'")
    public List<Country> findAllCountries() {
        LOGGER.debug("------------------Find All Countries inside method");
        return countryList;
    }

    @Resource
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }
}
