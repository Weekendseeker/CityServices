package org.marvin.models.responses;
import org.marvin.models.entities.ServiceCenter;

import java.util.List;

public class GetServiceCenterListResponse {

    private List<ServiceCenter> serviceCenterList;

    public void setServiceCenter(List<ServiceCenter>  serviceCenterList) {
        this.serviceCenterList = serviceCenterList;
    }

    public List<ServiceCenter>  getServiceCenterList() {
        return serviceCenterList;
    }

}
