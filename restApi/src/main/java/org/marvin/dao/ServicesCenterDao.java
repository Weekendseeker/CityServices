package org.marvin.dao;

import org.marvin.models.ServiceCenter;

import java.util.List;

public interface ServicesCenterDao {
    List<ServiceCenter> getAllServiceCenters();
    List<String> getCities();
    ServiceCenter getServiceCenterById(int id);
    List<String> getCountries();

    void createServiceCenter(ServiceCenter serviceCenter);
    void updateServiceCenter(ServiceCenter serviceCenter);
    void deleteServiceCenter(ServiceCenter serviceCenter);


}
