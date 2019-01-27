package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.commands.DeviceCommand;
import eu.seatter.homeheating.edge.model.Device;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 09:19
 */
public interface DeviceService extends CrudService<Device, Long>{

    Optional<Device> findByName(String name);
    Optional<Device> findBySerialNo(String serialNo);
    Optional<Device> newDevice(DeviceCommand deviceCommand);
}
