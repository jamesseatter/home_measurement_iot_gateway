package eu.seatter.homeheating.edge.controller;

import eu.seatter.homeheating.edge.commands.RegistrationCommand;
import eu.seatter.homeheating.edge.converters.DeviceCommandToDevice;
import eu.seatter.homeheating.edge.converters.DeviceToRegistrationCommand;
import eu.seatter.homeheating.edge.model.Device;
import eu.seatter.homeheating.edge.service.DeviceService;
import eu.seatter.homeheating.edge.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 25/01/2019
 * Time: 11:18
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    private final DeviceCommandToDevice convertDeviceCommandToDevice;
    private final DeviceToRegistrationCommand convertDeviceToRegistrationCommand;

    public RegistrationController(RegistrationService registrationService, DeviceService deviceService, DeviceCommandToDevice convertDeviceCommandToDevice, DeviceToRegistrationCommand convertDeviceToRegistrationCommand) {
        this.registrationService = registrationService;
        this.convertDeviceCommandToDevice = convertDeviceCommandToDevice;
        this.convertDeviceToRegistrationCommand = convertDeviceToRegistrationCommand;
    }

    @PostMapping(value = {"device/new","device/new"}, consumes = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationCommand newDevice(@Validated @RequestBody RegistrationCommand registrationCommand) {
        Device device = registrationService.newDevice(registrationCommand).orElseGet(Device::new);
        return convertDeviceToRegistrationCommand.convert(device);
    }

    @PostMapping(value = {"device","device"}, consumes = "text/plain;charset=UTF-8")
    public RegistrationCommand getDeviceRegistration(@RequestBody String uniqueId) {
        Device device = registrationService.getRegistration(uniqueId).orElseGet(Device::new);
        return convertDeviceToRegistrationCommand.convert(device);
    }
}
