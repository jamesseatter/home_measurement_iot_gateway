package eu.seatter.homeheating.edge.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
@AllArgsConstructor
@Builder
@Entity
@Table(name = "device")
public class Device extends BaseEntity {

    @NotNull
    @Size(max = 30)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(max = 50)
    @Column(name = "serialno")
    private String serialNo;

    @NotNull
    @Size(max = 50)
    @Column(name = "manufacturer")
    private String manufacturer;

    @NotNull
    @Size(max = 50)
    @Column(name = "operatingsystem")
    private String operatingSystem;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "device", orphanRemoval = true)
    private Set<Sensor> sensors = new HashSet<>();


}
