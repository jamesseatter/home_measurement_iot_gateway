package eu.seatter.homemeasurement.gateway.commands;

import eu.seatter.homemeasurement.gateway.model.SensorType;
import eu.seatter.homemeasurement.gateway.model.SensorValueType;
import eu.seatter.homemeasurement.gateway.model.SensorValueUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
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
public class SensorCommand{
    @NotNull
    private Long id;
    @NotNull
    private String sensorId;  // Unique ID for a sensor. Can be the OneWire ID or a made up unique ID.
    @NotNull
    private SensorType sensorType;
    @NotNull
    private SensorValueType valueType;  // for example, Temperature, Humidity
    @NotNull
    private SensorValueUnit valueUnit;  // for example, Centigrade
    @NotNull
    private LocalDateTime dateAdded;
    @NotNull
    private LocalDateTime dateModified;
    @NotNull
    private Boolean active;
    @NotNull
    private DeviceCommand device;

}
