package eu.seatter.homeheating.edge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 31/12/2018
 * Time: 12:01
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "sensor")
public class Sensor extends BaseEntity {

    @Column(name = "sensorType")
    private String sensorType;

    @Column(name = "valueType")
    private String valueType;  // for example, Temperature, Humidity

    @Column(name = "valueUnit")
    private String valueUnit;  // for example, Centigrade

    @Column(name = "dataAdded")  // when the sensor is added update this field, UTC value.
    private LocalDateTime dateAdded;

    @Column(name = "dataModified")  // each time the sensor record is altered update this field, UTC value.
    private LocalDateTime dateModified;

    @Column(name = "active")    // True - sensor is in active use
    private Boolean active;


}
