package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.exceptions.DeviceNotFoundException;
import eu.seatter.homeheating.edge.model.*;
import eu.seatter.homeheating.edge.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 12:38
 */
@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;
    private final DeviceService deviceService;

    @Autowired
    public SensorServiceImpl(SensorRepository sensorRepository, DeviceService deviceService) {
        this.sensorRepository = sensorRepository;
        this.deviceService = deviceService;
    }

    @Override
    @Transactional
    public Optional<Sensor> findBySensorId(String sensorId) {
        return sensorRepository.findBySensorId(sensorId);
    }

    @Override
    public Sensor addSensor(String sensorId, SensorType sensorType, SensorValueType valueType, SensorValueUnit valueUnit, boolean active, Device device) {

        Device deviceInDB = deviceService.findBySerialNo(device.getSerialNo()).orElseThrow(() ->
                new DeviceNotFoundException("Device with SN " + device.getSerialNo() + " not found",
                        "Verify the Serial Number is correct and the device is registered with the system."));

        Sensor sensor = new Sensor();
        sensor.setActive(active);
        sensor.setDateAdded(LocalDateTime.now(ZoneOffset.UTC));
        sensor.setDateModified(LocalDateTime.now(ZoneOffset.UTC));
        sensor.setSensorId(sensorId);
        sensor.setSensorType(sensorType);
        sensor.setValueType(valueType);
        sensor.setValueUnit(valueUnit);
        sensor.setDevice(device);

        return sensorRepository.save(sensor);
    }

    @Override
    @Transactional
    public Set<Sensor> findAll() {
        Set<Sensor> sensors = new HashSet<>();
        sensorRepository.findAll().forEach(sensors::add);
        return sensors;
    }

    @Override
    @Transactional
    public Set<Sensor> findAllByDevice(Device device) {
        Set<Sensor> sensors = new HashSet<>();
        sensorRepository.findAllByDevice(device).forEach(sensors::add);
        return sensors;
    }

    @Override
    @Transactional
    public Optional<Sensor> findById(Long id) {
        return sensorRepository.findById(id);
    }

    @Override
    public Optional<Sensor> save(Sensor object) {
        return Optional.ofNullable(sensorRepository.save(object));
    }

    @Override
    public void delete(Sensor object) {
        sensorRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        sensorRepository.deleteById(id);
    }
}
