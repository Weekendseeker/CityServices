package org.marvin.models.responses;

import org.marvin.models.entities.City;

import java.util.List;

public class GetCitiesResponse {

    private List<City> cities;

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
