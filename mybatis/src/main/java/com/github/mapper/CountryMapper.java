package com.github.mapper;

import com.github.bean.Country;

import java.util.List;

/**
 * Created by xiedan on 2017/3/7.
 */
public interface CountryMapper {
    int insertList(List<Country> countryList);
    Country getCountryById(int id);
}
