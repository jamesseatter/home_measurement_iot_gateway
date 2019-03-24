package eu.seatter.homemeasurement.gateway.service;

import eu.seatter.homemeasurement.gateway.commands.DeviceCommand;
import eu.seatter.homemeasurement.gateway.model.Device;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 09:19
 */
public interface DeviceService extends CrudService<Device, Long>{

    Optional<Device> findByName(String name);
    Optional<Device> findByUniqueId(String uniqueId);
    Optional<Device> newDevice(DeviceCommand deviceCommand);
}
