package eu.seatter.homeheating.edge.converters;

import eu.seatter.homeheating.edge.commands.MeasurementCommand;
import eu.seatter.homeheating.edge.model.Measurement;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 18/01/2019
 * Time: 23:53
 */
@Component
public class MeasurementToMeasurementCommand implements Converter<Measurement, MeasurementCommand> {
    private final SensorToSensorCommand sensorToSensorCommand;

    public MeasurementToMeasurementCommand(SensorToSensorCommand sensorToSensorCommand) {
        this.sensorToSensorCommand = sensorToSensorCommand;
    }

    @Override
    public MeasurementCommand convert(Measurement source) {
        if (source == null) {
            return null;
        }

        final MeasurementCommand dest = new MeasurementCommand();

        dest.setId(source.getId());
        dest.setValue(source.getValue());
        dest.setMeasurementTime(source.getMeasurementTime());
        dest.setSensor(sensorToSensorCommand.convert(source.getSensor()));

        return dest;
    }
}
