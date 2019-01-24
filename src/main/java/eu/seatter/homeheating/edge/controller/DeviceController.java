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
    public List<DeviceCommand> getAllDevices() {
        log.debug("Entered getAllSensors");
        Set<Device> foundSensors = deviceService.findAll();

        return foundSensors.stream()
                .sorted()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public DeviceCommand getDeviceBySerialNumber(@PathVariable Long id) {
        log.debug("Entered getDeviceBySerialNumber, id=" + id);
        Device foundDevice =  deviceService.findById(id).orElseThrow(() ->
                new DeviceNotFoundException("Device with ID " + id + " not found",
                        "Verify the ID is correct and the device is registered with the system."));
        return converter.convert(foundDevice);
    }

    @GetMapping(value = {"","/"}, params = "name", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public DeviceCommand getDeviceByName(@RequestParam String name) {
        log.debug("Entered getDeviceByName, name=" + name);
        Device foundDevice = deviceService.findByName(name).orElseThrow(() ->
                new DeviceNotFoundException("Device with Name " + name + " not found",
                        "Verify the Name is correct and the device is registered with the system."));

        return converter.convert(foundDevice);
    }

    @GetMapping(value = {"","/"}, params = "serialno", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public DeviceCommand getDeviceBySerialNo(@RequestParam String serialno) {
        log.debug("Entered getDeviceBySerialNo, serialno=" + serialno);
        Device foundDevice =  deviceService.findBySerialNo(serialno).orElseThrow(() ->
                new DeviceNotFoundException("Device with SN " + serialno + " not found",
                        "Verify the Serial Number is correct and the device is registered with the system."));

        return converter.convert(foundDevice);
    }
}
