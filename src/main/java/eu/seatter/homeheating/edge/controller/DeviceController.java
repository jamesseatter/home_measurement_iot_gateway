package eu.seatter.homeheating.edge.controller;

import eu.seatter.homeheating.edge.commands.DeviceCommand;
import eu.seatter.homeheating.edge.converters.DeviceToDeviceCommand;
import eu.seatter.homeheating.edge.exceptions.DeviceNotFoundException;
import eu.seatter.homeheating.edge.model.Device;
import eu.seatter.homeheating.edge.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 21:31
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/device")

public class DeviceController {

    private final DeviceService deviceService;

    private final DeviceToDeviceCommand converter;

    public DeviceController(DeviceService deviceService, DeviceToDeviceCommand converter) {
        this.deviceService = deviceService;
        this.converter = converter;
    }

    @GetMapping(value = {"","/"}, produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public List<DeviceCommand> getAllSensors() {
        log.debug("Entered getAllSensors");
        Set<Device> foundSensors = deviceService.findAll();

        return foundSensors.stream()
                .sorted()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{serialno}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public Device getDeviceBySerialNumber(@PathVariable String serialno) {
        return deviceService.findBySerialNo(serialno).orElseThrow(() ->
                new DeviceNotFoundException("Device with SN " + serialno + " not found",
                        "Verify the Serial Number is correct and the device is registered with the system."));
    }


}
