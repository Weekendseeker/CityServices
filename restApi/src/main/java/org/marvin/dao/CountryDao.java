package org.marvin.dao;

import org.marvin.models.entities.Country;

import java.util.List;

public interface CountryDao {
    Country getCountryById(long id);
    Country getCountryByName(String name);
    List<Country> countryList();

    void update(Country country);
    void insert(Country country);
    void deleteById(long id);

    boolean validateAtributes(Country other);

}
