package org.marvin.dao.impls;

import org.hibernate.Session;
import org.marvin.dao.CountryDao;
import org.marvin.models.entities.Country;
import org.marvin.utils.DataBaseConnector;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Service("countryDao")
public class CountryDaoImpl extends DataBaseConnector implements CountryDao {

    @Override
    public Country getCountryById(long id) throws NoResultException {

        Session session = openSession();

        Query query = session.createQuery("FROM COUNTRY WHERE ID = :id");
        query.setParameter("id", id);

        Country country = (Country) query.getSingleResult();

        closeSession(session);

        return country;
    }

    @Override
    public Country getCountryByName(String name) {

        Session session = openSession();

        Query query = session.createQuery("FROM COUNTRY WHERE NAME = :name");
        query.setParameter("name", name);

        Country country = (Country) query.getSingleResult();

        closeSession(session);

        return country;
    }

    @Override
    public List<Country> countryList() {

        Session session = openSession();

        Query query = session.createQuery("FROM COUNTRY");

        List<Country> listCountry = (List<Country>)query.getResultList();

        closeSession(session);

        return listCountry;
    }

    @Override
    public void update(Country country) {

        validateAtributes(country);

        Session session = openSession();
        session.beginTransaction();

        session.update(country);

        session.getTransaction().commit();

        closeSession(session);

    }

    @Override
    public void insert(Country country) {

        validateAtributes(country);

        Session session = openSession();
        session.beginTransaction();

        session.save(country);

        session.getTransaction().commit();

        closeSession(session);

    }

    @Override
    public void deleteById(long id) {

        getCountryById(id);

        Session session = openSession();
        session.beginTransaction();

        Query query = session.createQuery("DELETE FROM COUNTRY WHERE ID =: id");
        query.setParameter("id",id);

        session.getTransaction().commit();

        closeSession(session);

    }

    public boolean validateAtributes(Country country){

        Country country1 = getCountryById(country.getId());
        if(!country1.equals(country)) throw new IllegalArgumentException("Bad args for country");

        return true;
    }
}
