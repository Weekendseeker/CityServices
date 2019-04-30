package org.marvin.models;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "SERVICE_CENTER")
@Table(name = "SERVICE_CENTER")
public class ServiceCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CITY_NAME")
    private String cityName;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @Column(name = "ADDRESS")
    private String address;

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "SERVICE_CENTER_MAINTENANCE",
            joinColumns = @JoinColumn(name = "SERVICE_CENTER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MAINTENANCE_ID")
    )
    private Set<Maintenance> maintenancies;

    public ServiceCenter(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Maintenance> getMaintenances() {
        return maintenancies;
    }

    public void setMaintenances(Set<Maintenance> maintenancies) {
        this.maintenancies = maintenancies;
    }

    @Override
    public String toString() {
        return String.format(" Country:%s\n Name: %s\n City: %s\n Address: %s\n Maintenancies: %s\n",
                               name,countryName,cityName, address, maintenancies);
    }

}
