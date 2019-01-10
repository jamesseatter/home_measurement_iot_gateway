package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.model.Device;
import eu.seatter.homeheating.edge.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 09:19
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository repository;

    @Override
    public Optional<Device> getDeviceByName(String name) {
        return Optional.ofNullable(repository.findByName(name));
    }

    @Override
    public Optional<Device> getDeviceBySerialNo(String serialNo) {
        return Optional.ofNullable(repository.findBySerialNo(serialNo));
    }
}
