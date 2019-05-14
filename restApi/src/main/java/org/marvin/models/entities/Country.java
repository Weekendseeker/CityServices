package org.marvin.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity(name = "COUNTRY")
@Table(name = "COUNTRY")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Min(value = 1 ,message ="Country id  must be > 0" )
    private long id;

    @Column(name = "NAME")
    @NotEmpty(message ="Country name must be not empty")
    @NotNull(message = "Country name must be not null")
    private String name;

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

    @Override
    public boolean equals(Object o) {

        Country country;
        try {
            country = (Country) o;
        }catch (ClassCastException e){
            return false;
        }
        return this.id == (country.getId()) && name.equals(country.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
