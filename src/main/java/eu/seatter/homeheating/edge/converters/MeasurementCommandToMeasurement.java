package eu.seatter.homeheating.edge.converters;

import eu.seatter.homeheating.edge.commands.MeasurementCommand;
import eu.seatter.homeheating.edge.model.Measurement;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 18/01/2019
 * Time: 23:54
 */
@Component
public class MeasurementCommandToMeasurement implements Converter<MeasurementCommand, Measurement> {
    @Override
    public Measurement convert(MeasurementCommand source) {
        if (source == null) {
            return null;
        }

        final Measurement dest = new Measurement();

        dest.setId(source.getId());
        dest.setValue(source.getValue());
        dest.setMeasurementTime(source.getMeasurementTime());
        dest.setSensor(source.getSensor());

        return dest;
    }
}
