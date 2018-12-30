package eu.seatter.homeheating.edge.model;


import lombok.Builder;
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
 * Date: 24/12/2018
 * Time: 11:32
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "measurement")
public class Measurement extends BaseEntity {

    @Column(name = "measurementTime")
    private LocalDateTime measurementTime;

    @Column(name = "value")
    private Double value;

    @Column(name = "sensorId")
    private Long sensorId;

    @Column(name = "sensorType")
    private String sensorType;

    @Column(name = "measurementType")
    private String measurementType;

    @Builder
    public Measurement(Long id, LocalDateTime measurementTime, Double value, Long sensorId, String sensorType, String measurementType) {
        super(id);
        this.measurementTime = measurementTime;
        this.value = value;
        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.measurementType = measurementType;
    }
}
