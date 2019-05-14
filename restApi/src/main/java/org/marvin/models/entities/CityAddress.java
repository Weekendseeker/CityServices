package org.marvin.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity(name = "CITY_ADDRESS")
@Table(name = "CITY_ADDRESS")
public class CityAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long streetId;

    @Column(name = "STREET_NAME")

    @NotEmpty(message ="Street name must be not empty")
    @NotNull(message = "Street name must be not null")
    private String streetName;

    @Column(name = "STREET_MARK")
    @NotEmpty(message ="Street mark must be not empty")
    @NotNull(message = "Street mark must be not null")
    private String streetMark;

    public long getStreetId() {
        return streetId;
    }

    public void setStreetId(long streetId) {
        this.streetId = streetId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetMark() {
        return streetMark;
    }

    public void setStreetMark(String streetMark) {
        this.streetMark = streetMark;
    }


    @Override
    public boolean equals(Object o) {

        CityAddress address ;
        try {
            address = (CityAddress) o;
        }catch (ClassCastException e){
            return false;
        }

        return this.streetId == (address.getStreetId()) &&
               this.streetName == (address.getStreetName()) &&
               this.streetMark == (address.getStreetMark()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetId, streetName, streetMark);
    }
}
