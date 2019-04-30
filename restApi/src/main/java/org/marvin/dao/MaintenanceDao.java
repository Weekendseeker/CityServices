package org.marvin.dao;

import org.marvin.models.Maintenance;

import java.util.List;

public interface MaintenanceDao {

    void create(Maintenance maintenance);
    Maintenance getServiceById(int id);
    void update(Maintenance maintenance);
    void delete(Maintenance maintenance);
    List<Maintenance> getAllService();

}
