package org.marvin.controllers;

import org.marvin.dao.CitiesDao;
import org.marvin.dao.CountryDao;
import org.marvin.dao.MaintenanceDao;
import org.marvin.dao.ServicesCenterDao;

import org.marvin.models.entities.*;
import org.marvin.models.responses.GetCitiesResponse;
import org.marvin.models.responses.GetCountriesResponses;
import org.marvin.models.responses.GetMaintenanciesResponse;
import org.marvin.models.responses.GetServiceCenterListResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.util.*;

@RestController
public class ServiceCenterController {

    private ServicesCenterDao servicesDao;
    private MaintenanceDao maintenanceDao;
    private CitiesDao cityDao;
    private CountryDao countryDao;

    //Получение списка данных о всех сервис центрах
    @RequestMapping(value = "/serviceList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GetServiceCenterListResponse> serviceCenterList() {

        List<ServiceCenter> serviceCenter = servicesDao.getAllServiceCenters();

        if (serviceCenter == null || serviceCenter.isEmpty())
            return new ResponseEntity("ServiceCenter's not yet", HttpStatus.BAD_REQUEST);

        GetServiceCenterListResponse sclr = new GetServiceCenterListResponse();
        sclr.setServiceCenter(serviceCenter);

        return new ResponseEntity<>(sclr, HttpStatus.OK);
    }

    //Получение сервиса по ID
    @RequestMapping(value = "/getServiceCenterById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ServiceCenter> getServiceCenterById(@RequestParam("id") String id) {

        ServiceCenter serviceCenter = null;
        try {
            try {
                serviceCenter = servicesDao.getServiceCenterById(Integer.parseInt(id));
            } catch (NoResultException e) {
                return new ResponseEntity("Bab format id", HttpStatus.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new ResponseEntity("ServiceCenter not found", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(serviceCenter, HttpStatus.OK);
    }

    //Получение списка городов в которых есть сервисы центры
    @RequestMapping(value = "/citiesList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GetCitiesResponse> citiesList() {

        GetCitiesResponse getCitiesResponse;

        try {
            List<City> cities = cityDao.cityList();

            getCitiesResponse = new GetCitiesResponse();
            getCitiesResponse.setCities(cities);

        }catch (NoResultException e) {
            return new ResponseEntity("Bab format id", HttpStatus.BAD_REQUEST);
        }

        return  new ResponseEntity<>(getCitiesResponse, HttpStatus.OK );
    }

    //Получение списка возможных услуг
    @RequestMapping(value = "/maintainciesList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GetMaintenanciesResponse> maintainciesList() {

        GetMaintenanciesResponse maintenanciesResponse;

        try {

            List<Maintenance> maintenancies = maintenanceDao.getAllMaintenancies();

            maintenanciesResponse = new GetMaintenanciesResponse();
            maintenanciesResponse.setMaintenanciesList(maintenancies);

        }catch (NoResultException e) {
            return new ResponseEntity("Bab format id", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(maintenanciesResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/countriesList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GetCountriesResponses> countriesList() {

        GetCountriesResponses countriesResponses;

        try {

            List<Country> maintenancies = countryDao.countryList();

            countriesResponses = new GetCountriesResponses();
            countriesResponses.setCountries(maintenancies);

        }catch (NoResultException e) {
            return new ResponseEntity("Bab format id", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(countriesResponses, HttpStatus.OK);
    }

    //Добавление нового сервис центра
    @RequestMapping(value = "/addServiceCenter", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addServiceCenter(@Valid @RequestBody ServiceCenter serviceCenter) {

        servicesDao.createServiceCenter(serviceCenter);

        return new ResponseEntity<>(serviceCenter + "Was created!", HttpStatus.OK);
    }

    //Обновление существующего сервис центра
    @RequestMapping(value = "/updateServiceCenter", method = RequestMethod.PUT)
    @ResponseBody
    public String updateServiceCenter(@Valid @RequestBody ServiceCenter serviceCenter) {

        servicesDao.updateServiceCenter(serviceCenter);

        return String.format("%s \n was updated!\n", serviceCenter);
    }

    //Удаленин сервис центра
    @RequestMapping(value = "/deleteServiceCenter", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteServiceCenterById(@RequestParam("id") String id) {

        try {

            servicesDao.deleteServiceCenterById(Long.parseLong(id));

        } catch (NumberFormatException e) {
            return "Id mast be number";
        }catch (NoResultException e) {
            return String.format("Service center with id %s not exist!\n", id);
        }

        return String.format("Service center with id %s  was deleted!\n", id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @Autowired
    public void setServicesDao(ServicesCenterDao servicesDao) {
        this.servicesDao = servicesDao;
    }

    @Autowired
    public void setMaintenanceDao(MaintenanceDao maintenanceDao) {
        this.maintenanceDao = maintenanceDao;
    }

    @Autowired
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Autowired
    public void setCityDao(CitiesDao cityDao) {
        this.cityDao = cityDao;
    }

    public ServicesCenterDao getServicesDao() {
        return servicesDao;
    }

    public MaintenanceDao getMaintenanceDao() {
        return maintenanceDao;
    }

    public CitiesDao getCityDao() {
        return cityDao;
    }

    public CountryDao getCountryDao() {
        return countryDao;
    }
}
