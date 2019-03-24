package eu.seatter.homemeasurement.gateway.service;

import eu.seatter.homemeasurement.gateway.commands.DeviceCommand;
import eu.seatter.homemeasurement.gateway.commands.RegistrationCommand;
import eu.seatter.homemeasurement.gateway.converters.RegistrationCommandToDevice;
import eu.seatter.homemeasurement.gateway.converters.RegistrationCommandToDeviceCommand;
import eu.seatter.homemeasurement.gateway.model.Device;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 25/01/2019
 * Time: 11:25
 */
@Slf4j
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
        log.debug("Register new device : " + registrationCommand.getName());
        Optional<Device> deviceInDB = deviceService.findByUniqueId(registrationCommand.getUniqueId());
        if(deviceInDB.isPresent()) {
            log.debug("Device already registered");
            return deviceInDB;
        } else {
            DeviceCommand deviceCommand = converterRegistrationCommandToDeviceCommand.convert(registrationCommand);
            log.debug("Device now registered");
            return deviceService.newDevice(deviceCommand);
        }
    }

    @Override
    public Optional<Device> getRegistration(String uniqueId) {
        log.debug("Getting registration details for device with uniqueId: " + uniqueId);
        return deviceService.findByUniqueId(uniqueId);
    }
}
