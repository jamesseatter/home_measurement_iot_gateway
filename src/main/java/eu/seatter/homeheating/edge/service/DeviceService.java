package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.model.Device;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 09:19
 */
public interface DeviceService {

    Optional<Device> getDeviceByName(String name);
    Optional<Device> getDeviceBySerialNo(String serialNo);
}
