package eu.seatter.homemeasurement.gateway.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class MeasurementCommand{
    @NotNull
    private Long id;
    @NotNull
    private LocalDateTime measurementTime;
    @NotNull
    private Double value;
    @NotNull
    private SensorCommand sensor;

}
