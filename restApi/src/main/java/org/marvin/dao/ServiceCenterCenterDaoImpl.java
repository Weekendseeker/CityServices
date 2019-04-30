package org.marvin.dao;

import org.hibernate.Session;

import org.hibernate.query.NativeQuery;
import org.marvin.models.Maintenance;
import org.marvin.models.ServiceCenter;
import org.marvin.utils.DataBaseConnector;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Service("servicesCenterDao")
public class ServiceCenterCenterDaoImpl extends DataBaseConnector implements ServicesCenterDao {


    @Override
    public List<ServiceCenter> getAllServiceCenters() {

        Session session = openSession();
        List<ServiceCenter> result = (List<ServiceCenter>)session.createQuery(" FROM SERVICE_CENTER").getResultList();

        return result ;
    }

    @Override
    public List<String> getCities() {
        Session session = openSession();
        List<String> result = session.createQuery("SELECT cityName FROM SERVICE_CENTER").getResultList();

        return result;
    }

    @Override
    public List<String> getCountries() {
        Session session = openSession();
        List<String> result = session.createQuery("SELECT countryName FROM SERVICE_CENTER").getResultList();


        return result;
    }

    @Override
    public ServiceCenter getServiceCenterById(int id) {
        Session session = openSession();


        Query query = session.createQuery("FROM  SERVICE_CENTER WHERE ID = :id");
        query.setParameter("id",id);

        ServiceCenter serviceCenter = (ServiceCenter) query.getSingleResult();


        return serviceCenter;
    }

    @Override

    public void createServiceCenter(ServiceCenter serviceCenter) {

        Session session = openSession();
        session.beginTransaction();

        session.save(serviceCenter);

        session.getTransaction().commit();
    }

    @Override
    public void updateServiceCenter(ServiceCenter serviceCenter) {

        Session session = openSession();
        session.beginTransaction();

        session.update(serviceCenter);
        session.getTransaction().commit();

    }

    @Override
    public void deleteServiceCenter(ServiceCenter serviceCenter) {
        Session session = openSession();

        session.beginTransaction();
        session.remove(serviceCenter);
        session.getTransaction().commit();
    }
}
