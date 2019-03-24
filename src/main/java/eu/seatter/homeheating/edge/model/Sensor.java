package eu.seatter.homeheating.edge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 31/12/2018
 * Time: 12:01
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sensor")
public class Sensor extends BaseEntity implements Comparable<Sensor> {

    @NotNull
    @Size(max=20)
    @Column(name="sensorid")
    private String sensorId;  // Unique ID for a sensor. Can be the OneWire ID or a made up unique ID.

    @NotNull
    @Column(name = "sensortype")
    @Enumerated(value = EnumType.STRING)
    private SensorType sensorType;

    @NotNull
    @Column(name = "valuetype")
    @Enumerated(value = EnumType.STRING)
    private SensorValueType valueType;  // for example, Temperature, Humidity

    @NotNull
    @Column(name = "valueunit")
    @Enumerated(value = EnumType.STRING)
    private SensorValueUnit valueUnit;  // for example, Centigrade

    @NotNull
    @Column(name = "dateadded")  // when the sensor is added update this field, UTC value.
    private LocalDateTime dateAdded;

    @NotNull
    @Column(name = "datemodified")  // each time the sensor record is altered update this field, UTC value.
    private LocalDateTime dateModified;

    @NotNull
    @Column(name = "active")    // True - sensor is in active use
    private Boolean active;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "device_id", nullable = false)
    @ManyToOne
    @JsonIgnore
    private Device device;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sensor", orphanRemoval = true)
    @JsonIgnore
    private Set<Measurement> measurements = new HashSet<>();

    @Override
    public int compareTo(Sensor o) {
        return super.getId().compareTo(o.getId());
    }
}
