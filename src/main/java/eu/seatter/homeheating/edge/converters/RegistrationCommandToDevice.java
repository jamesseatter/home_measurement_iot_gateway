package eu.seatter.homeheating.edge.converters;

import eu.seatter.homeheating.edge.commands.RegistrationCommand;
import eu.seatter.homeheating.edge.model.Device;
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
public class RegistrationCommandToDevice implements Converter<RegistrationCommand, Device> {
    @Nullable
    @Override
    public Device convert(RegistrationCommand source) {
        if (source == null) {
            return null;
        }

        final Device dest = new Device();

        dest.setId(source.getId());
        dest.setName(source.getName());
        dest.setManufacturer(source.getManufacturer());
        dest.setOperatingSystem(source.getOperatingSystem());
        dest.setSerialNo(source.getSerialNo());
        dest.setRegistrationStatus(source.getRegistrationStatus());
        dest.setRegistrationCode(source.getRegistrationCode());
        return dest;
    }
}
