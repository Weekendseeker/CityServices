package org.marvin.models.responses;

import org.marvin.models.entities.Country;

import java.util.List;

public class GetCountriesResponses {

    private List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

}
