package org.marvin.dao.impls;

import org.hibernate.Session;

import org.marvin.dao.CitiesDao;
import org.marvin.models.entities.City;
import org.marvin.utils.DataBaseConnector;

import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service("cityDao")
public class CityDaoImpl extends DataBaseConnector implements CitiesDao {

    @Override
    public City getCityById(long id) {

        Session session = openSession();

        Query query = session.createQuery("FROM CITY WHERE ID = :id");
        query.setParameter("id", id);

        City city = (City) query.getSingleResult();

        closeSession(session);

        return city;

    }

    @Override
    public City getCityByName(String name) {

        Session session = openSession();

        Query query = session.createQuery("FROM CITY WHERE NAME = :name");
        query.setParameter("name", name);

        City city = (City) query.getSingleResult();

        closeSession(session);

        return city;
    }

    @Override
    public List<City> cityList() {

        Session session = openSession();

        Query query = session.createQuery("FROM CITY");

        List<City> listCountry = (List<City>)query.getResultList();

        closeSession(session);

        return listCountry;
    }

    @Override
    public void update(City city) {

        Session session = openSession();
        session.beginTransaction();

        session.update(city);

        session.getTransaction().commit();

        closeSession(session);

    }

    @Override
    public void insert(City country) {

        Session session = openSession();
        session.beginTransaction();

        session.update(country);

        session.getTransaction().commit();

        closeSession(session);
    }

    @Override
    public void deleteById(long id) {

        Session session = openSession();
        session.beginTransaction();

        Query query = session.createQuery("DELETE FROM CITY WHERE ID = :id ");
        query.setParameter("id",id);

        session.getTransaction().commit();

        closeSession(session);

    }

    public boolean validateAtributes(City city){

        City bdCity = getCityById(city.getId());
        if(!city.equals(bdCity)) throw new IllegalArgumentException("Bad args for city");

        return true;
    }
}
