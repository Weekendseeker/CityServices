package org.marvin.dao;

import org.marvin.models.entities.City;
import java.util.List;

public interface CitiesDao {

    City getCityById(long id);
    City getCityByName(String name);
    List<City> cityList();

    void update(City country);
    void insert(City country);
    void deleteById(long id);

    boolean validateAtributes(City other);

}
