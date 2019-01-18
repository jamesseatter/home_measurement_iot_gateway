package eu.seatter.homeheating.edge.converters;

import eu.seatter.homeheating.edge.commands.SensorCommand;
import eu.seatter.homeheating.edge.model.Sensor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 18/01/2019
 * Time: 23:48
 */
@Component
public class SensorCommandToSensor implements Converter<SensorCommand, Sensor> {
    @Override
    public Sensor convert(SensorCommand source) {
        if (source == null) {
            return null;
        }
        final Sensor dest = new Sensor();

        dest.setId(source.getId());
        dest.setActive(source.getActive());
        dest.setSensorId(source.getSensorId());
        dest.setSensorType(source.getSensorType());
        dest.setValueType(source.getValueType());
        dest.setValueUnit(source.getValueUnit());
        dest.setDateAdded(source.getDateAdded());
        dest.setDateModified(source.getDateModified());
        dest.setDevice(source.getDevice());

        return dest;
    }
}
