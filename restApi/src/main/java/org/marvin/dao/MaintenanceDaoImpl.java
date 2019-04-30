package org.marvin.dao;

import org.hibernate.Session;
import org.marvin.utils.DataBaseConnector;
import org.marvin.models.Maintenance;
import org.springframework.stereotype.Service;
import java.util.List;


@Service("maintenanceDao")
public class MaintenanceDaoImpl extends DataBaseConnector implements MaintenanceDao {

    @Override
    public void create(Maintenance maintenance) {

    }

    @Override
    public Maintenance getServiceById(int id) {
        return null;
    }

    @Override
    public void update(Maintenance maintenance) {

    }

    @Override
    public void delete(Maintenance maintenance) {

    }

    @Override
    public List<Maintenance> getAllService() {
        Session session = openSession();
        List<Maintenance> maintenancesList = (List<Maintenance>)session.createQuery("FROM MAINTENANCE").getResultList();

        session.close();

        return maintenancesList;
    }

}
