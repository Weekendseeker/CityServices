package org.marvin.models.responses;

import org.marvin.models.entities.Maintenance;

import java.util.List;

public class GetMaintenanciesResponse {

    private List<Maintenance> maintenanciesList;

    public List<Maintenance> getMaintenanciesList() {
        return maintenanciesList;
    }

    public void setMaintenanciesList(List<Maintenance> maintenanciesList) {
        this.maintenanciesList = maintenanciesList;
    }
}
