package org.marvin.dao.impls;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import org.marvin.dao.CitiesDao;
import org.marvin.dao.CountryDao;
import org.marvin.dao.MaintenanceDao;
import org.marvin.dao.ServicesCenterDao;
import org.marvin.models.entities.Location;
import org.marvin.models.entities.Maintenance;
import org.marvin.models.entities.ServiceCenter;
import org.marvin.utils.DataBaseConnector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.validation.ValidationException;
import java.util.List;

@Service("servicesCenterDao")
public class ServiceCenterCenterDaoImpl extends DataBaseConnector implements ServicesCenterDao {

    private CountryDao countryDao;
    private MaintenanceDao maintenanceDao;
    private CitiesDao cityDao;

    @Override
    public List<ServiceCenter> getAllServiceCenters()  {

        Session session = openSession();
        List<ServiceCenter> result = (List<ServiceCenter>)session.createQuery(" FROM SERVICE_CENTER").getResultList();

        for (ServiceCenter serviceCenter: result){

            Hibernate.initialize(serviceCenter.getLocations());
            Hibernate.initialize(serviceCenter.getMaintenancies());
        }

        closeSession(session);

        return result ;
    }

    @Override
    public ServiceCenter getServiceCenterById(long id) {

        Session session = openSession();

        Query query = session.createQuery("FROM  SERVICE_CENTER WHERE ID = :id");
        query.setParameter("id",id);

        ServiceCenter serviceCenter = (ServiceCenter) query.getSingleResult();

        Hibernate.initialize(serviceCenter.getLocations());
        Hibernate.initialize(serviceCenter.getMaintenancies());

        closeSession(session);

        return serviceCenter;
    }

    @Override
    public void createServiceCenter(ServiceCenter serviceCenter) {

        if(!validateAtributes(serviceCenter)) throw new ValidationException("Error with validation");

        Session session = openSession();



        session.beginTransaction();

        session.save(serviceCenter);

        session.getTransaction().commit();

        closeSession(session);
    }

    @Override
    public void updateServiceCenter(ServiceCenter serviceCenter) {

        if(!validateAtributes(serviceCenter)) throw new ValidationException("Error with validation");

        Session session = openSession();
        session.beginTransaction();

        session.update(serviceCenter);

        session.getTransaction().commit();

        closeSession(session);
    }

    @Override
    public void deleteServiceCenterById(long id) {

        getServiceCenterById(id);

        Session session = openSession();
        session.beginTransaction();

        Query query = session.createQuery("DELETE FROM SERVICE_CENTER WHERE ID = :id ");
        query.setParameter("id",id);

        query.executeUpdate();

        session.getTransaction().commit();

        closeSession(session);
    }


    public boolean validateAtributes(ServiceCenter center){

        try {

            for(Location location : center.getLocations()) {
                    cityDao.validateAtributes(location.getCity());
                    countryDao.validateAtributes(location.getCountry());
            }

            for (Maintenance maintenance : center.getMaintenancies()){
                    maintenanceDao.validateAtributes(maintenance);
            }

            return true;

        }catch (NoResultException | IllegalArgumentException e){
            return false;
        }
    }

    public CountryDao getCountryDao() {
        return countryDao;
    }

    @Autowired
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public MaintenanceDao getMaintenanceDao() {
        return maintenanceDao;
    }

    @Autowired
    public void setMaintenanceDao(MaintenanceDao maintenanceDao) {
        this.maintenanceDao = maintenanceDao;
    }

    public CitiesDao getCityDao() {
        return cityDao;
    }

    @Autowired
    public void setCityDao(CitiesDao cityDao) {
        this.cityDao = cityDao;
    }

}
