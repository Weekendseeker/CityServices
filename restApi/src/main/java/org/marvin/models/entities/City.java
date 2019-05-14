package org.marvin.models.entities;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity(name = "CITY")
@Table( name = "CITY")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Min(value = 1 ,message ="Id city must be > 0" )
    private long id;

    @Column(name = "NAME")
    @NotEmpty(message ="City name must be not empty")
    @NotNull(message = "City name must be not null")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

        City city ;

        try {
            city = (City) o;
        }catch (ClassCastException e){
             return false;
        }

        return this.id == (city.getId()) && name.equals(city.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
