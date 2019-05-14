package org.marvin.dao.impls;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.marvin.dao.MaintenanceDao;
import org.marvin.utils.DataBaseConnector;
import org.marvin.models.entities.Maintenance;
import org.springframework.stereotype.Service;
import javax.persistence.Query;
import java.util.List;

@Service("maintenanceDao")
public class MaintenanceDaoImpl extends DataBaseConnector implements MaintenanceDao {

    @Override
    public Maintenance getMaintenanceById(Long id) {

        Session session = openSession();

        Query query = session.createQuery("FROM MAINTENANCE WHERE ID = :id");
        query.setParameter("id", id);

        Maintenance maintenance = (Maintenance) query.getSingleResult();

        closeSession(session);

        return maintenance;
    }

    @Override
    public List<Maintenance> getAllMaintenancies() {

        Session session = openSession();
        List<Maintenance> maintenancesList = (List<Maintenance>)session.createQuery("FROM MAINTENANCE").getResultList();

        closeSession(session);

        return maintenancesList;
    }

    @Override
    public void insert(Maintenance maintenance) {

        Session session = openSession();
        session.beginTransaction();

        session.update(maintenance);

        session.getTransaction().commit();

        closeSession(session);

    }

    @Override
    public void update(Maintenance maintenance) {

        Session session = openSession();
        session.beginTransaction();

        session.update(maintenance);

        session.getTransaction().commit();

        closeSession(session);

    }

    @Override
    public void deleteById(long id){

        Session session = openSession();

        session.beginTransaction();

        Query query = session.createQuery("DELETE FROM MAINTENANCE WHERE ID =:id ");
        query.setParameter("id",id);

        session.getTransaction().commit();

        closeSession(session);

    }

    public boolean validateAtributes(Maintenance maintenance){

        Maintenance m = getMaintenanceById(maintenance.getId());
        if(!m.equals(maintenance)) throw new IllegalArgumentException("Bad args for maintenance");

        return true;
    }

}
