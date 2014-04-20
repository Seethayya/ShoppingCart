package com.seethayya.shoppingcart.service;

import com.seethayya.shoppingcart.dto.Country;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Seethaya
 * Date: 4/21/14
 * Time: 3:15 AM
 */
public interface CountryService {

    List<Country> findAllCountries();
}
