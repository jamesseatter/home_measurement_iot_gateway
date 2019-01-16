package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.model.Sensor;
import eu.seatter.homeheating.edge.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public Optional<Sensor> findBySensorId(String sensorId) {
        return sensorRepository.findBySensorId(sensorId);
    }

    @Override
    public Set<Sensor> findAll() {
        Set<Sensor> sensors = new HashSet<>();
        sensorRepository.findAll().forEach(sensors::add);
        return sensors;
    }

    @Override
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
