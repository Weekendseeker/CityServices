package org.marvin.models.entities;
import org.marvin.Main;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity(name = "MAINTENANCE")
@Table(name = "MAINTENANCE")
public class Maintenance {

    @Valid
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Min(value = 1 ,message ="Maintenance id must be > 0" )
    private long id;

    @Column(name = "DISCRIPTION")
    @NotEmpty(message ="Description maintenance must be not empty")
    @NotNull(message = "Description maintenance must be not null")
    private String descr;

    public Maintenance(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public String toString() {
        return "Discr:" + descr;
    }

    @Override
    public boolean equals(Object o) {

        Maintenance maintenance ;

        try {
            maintenance = (Maintenance) o;
        }catch (ClassCastException e){
            return false;
        }

        return this.id == (maintenance.getId()) && descr.equals(maintenance.getDescr());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descr);
    }
}
