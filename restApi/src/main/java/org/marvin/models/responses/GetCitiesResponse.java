package org.marvin.models.responses;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

public class GetCitiesResponse {

    private List<String> cities;

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}
