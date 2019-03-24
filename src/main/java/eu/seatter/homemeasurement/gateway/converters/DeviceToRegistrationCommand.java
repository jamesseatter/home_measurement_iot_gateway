package eu.seatter.homemeasurement.gateway.converters;

import eu.seatter.homemeasurement.gateway.commands.RegistrationCommand;
import eu.seatter.homemeasurement.gateway.model.Device;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 26/01/2019
 * Time: 09:56
 */
@Component
public class DeviceToRegistrationCommand implements Converter<Device, RegistrationCommand> {
    @Nullable
    @Override
    public RegistrationCommand convert(Device source) {
        if (source == null) {
            return null;
        }

        final RegistrationCommand dest = new RegistrationCommand();

        dest.setId(source.getId());
        dest.setName(source.getName());
        dest.setManufacturer(source.getManufacturer());
        dest.setOperatingSystem(source.getOperatingSystem());
        dest.setUniqueId(source.getUniqueId());
        dest.setRegistrationStatus(source.getRegistrationStatus());
        dest.setRegistrationCode(source.getRegistrationCode());
        return dest;
    }
}
