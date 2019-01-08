package eu.seatter.homeheating.edge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 01/01/2019
 * Time: 13:42
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "device")
public class Device extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "serialno")
    private String serialNo;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "operatingsystem")
    private String operatingSystem;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "device")
    private Set<Sensor> sensors = new HashSet<>();
}
