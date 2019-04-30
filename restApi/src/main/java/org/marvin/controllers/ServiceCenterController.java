package org.marvin.controllers;

import org.marvin.dao.MaintenanceDao;
import org.marvin.dao.ServicesCenterDao;

import org.marvin.models.Maintenance;
import org.marvin.models.ServiceCenter;
import org.marvin.models.responses.GetCitiesResponse;
import org.marvin.models.responses.GetMaintenanciesResponse;
import org.marvin.models.responses.GetServiceCenterListResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ServiceCenterController {

    private ServicesCenterDao servicesDao;

    private MaintenanceDao maintenanceDao;




    //Получение списка данных о всех сервис центрах
    @RequestMapping(value = "/serviceList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GetServiceCenterListResponse> getServiceCenterList(){

        List<ServiceCenter> serviceCenter = servicesDao.getAllServiceCenters();

        if(serviceCenter == null || serviceCenter.isEmpty())
            return new ResponseEntity("ServiceCenter's not yet", HttpStatus.BAD_REQUEST);

        GetServiceCenterListResponse sclr = new GetServiceCenterListResponse();
        sclr.setServiceCenter(serviceCenter);

        return new ResponseEntity<>(sclr,HttpStatus.OK) ;
    }


    //Получение сервиса по ID
    @RequestMapping(value = "/getServiceCenterById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ServiceCenter> getServiceCenterById(@RequestParam("id") String id ){

        ServiceCenter serviceCenter = servicesDao.getServiceCenterById(Integer.parseInt(id));

        if(serviceCenter == null)
            return new ResponseEntity("ServiceCenter not found", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(serviceCenter, HttpStatus.OK );
    }


    //Получение списка городов в которых есть сервисы центры
    @RequestMapping(value = "/getCities", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GetCitiesResponse> getCities( ){

        List<String> cities = servicesDao.getCities();
        if(cities == null)
            return new ResponseEntity("Cities not found", HttpStatus.BAD_REQUEST);

        GetCitiesResponse getCitiesResponse = new GetCitiesResponse();
        getCitiesResponse.setCities(cities);

        return new ResponseEntity<>(getCitiesResponse, HttpStatus.OK );
    }

    //Получение списка возможных услуг
    @RequestMapping(value = "/getMaintaincies", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GetMaintenanciesResponse> getMaintaincies( ){

        List<Maintenance> maintenancies = maintenanceDao.getAllService();

        if(maintenancies == null)
            return new ResponseEntity("Cities not found", HttpStatus.BAD_REQUEST);

        GetMaintenanciesResponse maintenanciesResponse = new GetMaintenanciesResponse();
        maintenanciesResponse.setMaintenanciesList(maintenancies);

        return new ResponseEntity<>(maintenanciesResponse, HttpStatus.OK );
    }


    //Добавление нового сервис центра
    @RequestMapping(value = "/addServiceCenter", method = RequestMethod.POST)
    public String addServiceCenter(@RequestBody ServiceCenter serviceCenter){


        servicesDao.createServiceCenter(serviceCenter);

        return String.format("%s \n was created!\n", serviceCenter);
    }

    //Обновление существующего сервис центра
    @RequestMapping(value = "/updateServiceCenter", method = RequestMethod.PUT)
    public String updateServiceCenter(@RequestBody ServiceCenter serviceCenter){

        servicesDao.updateServiceCenter(serviceCenter);

        return String.format("%s \n was updated!\n", serviceCenter);
    }
    //Удаленин сервис центра
    @RequestMapping(value = "/deleteServiceCenter", method = RequestMethod.DELETE)
    public String deleteServiceCenter(@RequestBody ServiceCenter serviceCenter){

        servicesDao.deleteServiceCenter(serviceCenter);

        return String.format("Service center with id %s  was deleted!\n", serviceCenter.getId());
    }


    @Autowired
    public void setServicesDao(ServicesCenterDao servicesDao) {
        this.servicesDao = servicesDao;
    }

    @Autowired
    public void setMaintenanceDao(MaintenanceDao maintenanceDao) {
        this.maintenanceDao = maintenanceDao;
    }

    public ServicesCenterDao getServicesDao() {
        return servicesDao;
    }

    public MaintenanceDao getMaintenanceDao() {
        return maintenanceDao;
    }
}
