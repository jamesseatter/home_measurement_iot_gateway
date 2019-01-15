package eu.seatter.homeheating.edge.controller;

import eu.seatter.homeheating.edge.exceptions.DeviceNotFoundException;
import eu.seatter.homeheating.edge.model.Device;
import eu.seatter.homeheating.edge.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    private final DeviceService service;

    @Autowired
    public DeviceController(DeviceService service) {
        this.service = service;
    }

    @GetMapping(value = "/{serialno}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public Device getDeviceBySerialNumber(@PathVariable String serialno) {
        return service.findBySerialNo(serialno).orElseThrow(() ->
                new DeviceNotFoundException("Device with SN " + serialno + " not found",
                        "Verify the Serial Number is correct and the device is registered with the system."));
    }


}
