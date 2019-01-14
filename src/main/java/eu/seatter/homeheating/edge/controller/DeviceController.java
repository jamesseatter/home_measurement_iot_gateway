package eu.seatter.homeheating.edge.controller;

import eu.seatter.homeheating.edge.exceptions.DeviceNotFound;
import eu.seatter.homeheating.edge.model.Device;
import eu.seatter.homeheating.edge.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @Autowired
    private DeviceService service;

//    public DeviceController(DeviceServiceImpl service) {
//        this.service = service;
//    }

    @GetMapping(value = "/{serialno}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public Device getDeviceBySerialNumber(@PathVariable String serialno) {
        try {
            return service.getDeviceBySerialNo(serialno).orElseThrow(() -> new DeviceNotFound("Device with SN " + serialno + " not found"));
        }
        catch (DeviceNotFound ex){
            log.error("Device not found with serial number : " + serialno);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }


}
