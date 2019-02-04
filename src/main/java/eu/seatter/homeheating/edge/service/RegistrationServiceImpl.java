package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.commands.DeviceCommand;
import eu.seatter.homeheating.edge.commands.RegistrationCommand;
import eu.seatter.homeheating.edge.converters.RegistrationCommandToDevice;
import eu.seatter.homeheating.edge.converters.RegistrationCommandToDeviceCommand;
import eu.seatter.homeheating.edge.model.Device;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 25/01/2019
 * Time: 11:25
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final DeviceService deviceService;
    private final RegistrationCommandToDeviceCommand converterRegistrationCommandToDeviceCommand;
    private final RegistrationCommandToDevice converterRegistrationCommandToDevice;

    public RegistrationServiceImpl(DeviceService deviceService, RegistrationCommandToDeviceCommand converterRegistrationCommandToDeviceCommand, RegistrationCommandToDevice converterRegistrationCommandToDevice) {
        this.deviceService = deviceService;
        this.converterRegistrationCommandToDeviceCommand = converterRegistrationCommandToDeviceCommand;
        this.converterRegistrationCommandToDevice = converterRegistrationCommandToDevice;
    }

    @Override
    public Optional<Device> newDevice(RegistrationCommand registrationCommand) {
        Optional<Device> deviceInDB = deviceService.findByUniqueId(registrationCommand.getUniqueId());
        if(deviceInDB.isPresent()) {
            return deviceInDB;
        } else {
            DeviceCommand deviceCommand = converterRegistrationCommandToDeviceCommand.convert(registrationCommand);
            return deviceService.newDevice(deviceCommand);
        }
    }

    @Override
    public Optional<Device> getRegistration(String uniqueId) {

        return deviceService.findByUniqueId(uniqueId);
    }
}
