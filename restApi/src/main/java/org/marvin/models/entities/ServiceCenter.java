package org.marvin.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity(name = "SERVICE_CENTER")
@Table(name = "SERVICE_CENTER")
@JsonIgnoreProperties(value = "id")
public class ServiceCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    @NotEmpty(message ="Service Center name must be not empty")
    @NotNull(message = "Service Center must be fill")
    private String name;

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "SERVICE_CENTER_MAINTENANCE",
            joinColumns = @JoinColumn(name = "SERVICE_CENTER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MAINTENANCE_ID")
    )
    @Valid
    private Set<Maintenance> maintenancies;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "SERVICE_CENTER_LOCATION",
            joinColumns = @JoinColumn(name = "SERVICE_CENTER_ID"),
            inverseJoinColumns = @JoinColumn(name = "LOCATION_ID")
    )

    @NotEmpty(message ="Service Center location name must be not empty")
    @NotNull(message = "Service Center location must be fill")
    @Valid
    private Set<Location> locations;

    public ServiceCenter(){ }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Maintenance> getMaintenancies() {
        return maintenancies;
    }

    public void setMaintenancies(Set<Maintenance> maintenancies) {
        this.maintenancies = maintenancies;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceCenter that = (ServiceCenter) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(maintenancies, that.maintenancies) &&
                Objects.equals(locations, that.locations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, maintenancies, locations);
    }
}
