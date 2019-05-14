package org.marvin.dao;

import org.marvin.models.entities.ServiceCenter;
import java.util.List;

public interface ServicesCenterDao {

    List<ServiceCenter> getAllServiceCenters();
    ServiceCenter getServiceCenterById(long id);

    void createServiceCenter(ServiceCenter serviceCenter);
    void updateServiceCenter(ServiceCenter serviceCenter);
    void deleteServiceCenterById(long id);

    boolean validateAtributes(ServiceCenter other);
}

