package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.model.Device;
import eu.seatter.homeheating.edge.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 09:19
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Optional<Device> findByName(String name) {
        return deviceRepository.findByName(name);
    }

    @Override
    public Optional<Device> findBySerialNo(String serialNo) {
        return deviceRepository.findBySerialNo(serialNo);
    }

    @Override
    public Set<Device> findAll() {
        Set<Device> devices = new HashSet<>();
        deviceRepository.findAll().forEach(devices::add);
        return devices;
    }

    @Override
    public Optional<Device> findById(Long id) {
        return Optional.ofNullable(deviceRepository.findById(id).orElse(new Device()));
    }

    @Override
    public Optional<Device> save(Device object) {
        return Optional.ofNullable(deviceRepository.save(object));
    }

    @Override
    public void delete(Device object) {
        deviceRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        deviceRepository.deleteById(id);
    }
}
