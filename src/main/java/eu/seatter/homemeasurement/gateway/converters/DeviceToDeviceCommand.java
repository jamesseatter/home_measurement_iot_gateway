package eu.seatter.homemeasurement.gateway.converters;

import eu.seatter.homemeasurement.gateway.commands.DeviceCommand;
import eu.seatter.homemeasurement.gateway.model.Device;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 18/01/2019
 * Time: 23:39
 */
@Component
public class DeviceToDeviceCommand implements Converter<Device, DeviceCommand> {
    @Nullable
    @Override
    public DeviceCommand convert(Device source) {
        if (source == null) {
            return null;
        }

        final DeviceCommand dest = new DeviceCommand();

        dest.setId(source.getId());
        dest.setName(source.getName());
        dest.setUniqueId(source.getUniqueId());
        dest.setOperatingSystem(source.getOperatingSystem());
        dest.setManufacturer(source.getManufacturer());

        return dest;
    }
}
