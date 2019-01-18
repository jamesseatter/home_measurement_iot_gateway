package eu.seatter.homeheating.edge.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 24/12/2018
 * Time: 11:32
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "measurement")
public class Measurement extends BaseEntity {

    @NotNull
    @Column(name = "measurementTime")
    private LocalDateTime measurementTime;

    @NotNull
    @Column(name = "value")
    private Double value;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, optional = false)
    @JoinColumn(name = "sensor_id", nullable = false)
    @JsonIgnore
    private Sensor sensor;

}
