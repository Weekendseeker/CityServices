package org.marvin.dao;

import org.marvin.models.entities.Maintenance;

import java.util.List;

public interface MaintenanceDao {

    List<Maintenance> getAllMaintenancies();
    Maintenance getMaintenanceById(Long id);

    void insert(Maintenance maintenance);
    void update(Maintenance maintenance);
    void deleteById(long id);

    boolean validateAtributes(Maintenance other);
}
