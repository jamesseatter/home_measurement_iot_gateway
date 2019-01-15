package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.model.Sensor;
import eu.seatter.homeheating.edge.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 12:38
 */
@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository repository;

    @Autowired
    public SensorServiceImpl(SensorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Sensor> getSensorBySensorId(String sensorId) {
        return Optional.ofNullable(repository.findBySensorId(sensorId));
    }
}
