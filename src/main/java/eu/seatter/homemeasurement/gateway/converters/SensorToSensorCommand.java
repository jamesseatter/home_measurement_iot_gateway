package eu.seatter.homemeasurement.gateway.converters;

import eu.seatter.homemeasurement.gateway.commands.DeviceCommand;
import eu.seatter.homemeasurement.gateway.commands.SensorCommand;
import eu.seatter.homemeasurement.gateway.model.Sensor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 18/01/2019
 * Time: 23:48
 */
@Component
public class SensorToSensorCommand implements Converter<Sensor, SensorCommand> {
    private final DeviceToDeviceCommand deviceToDeviceCommand;

    public SensorToSensorCommand(DeviceToDeviceCommand deviceToDeviceCommand) {
        this.deviceToDeviceCommand = deviceToDeviceCommand;
    }

    @Override
    public SensorCommand convert(Sensor source) {
        if (source == null) {
            return null;
        }

        final SensorCommand dest = new SensorCommand();
        DeviceCommand deviceCommand = deviceToDeviceCommand.convert(source.getDevice());

        dest.setId(source.getId());
        dest.setActive(source.getActive());
        dest.setSensorId(source.getSensorId());
        dest.setSensorType(source.getSensorType());
        dest.setValueType(source.getValueType());
        dest.setValueUnit(source.getValueUnit());
        dest.setDateAdded(source.getDateAdded());
        dest.setDateModified(source.getDateModified());
        dest.setDevice(deviceCommand);

        return dest;
    }
}
