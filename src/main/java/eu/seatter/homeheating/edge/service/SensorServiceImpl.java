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

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public Optional<Sensor> getSensorBySensorId(String sensorId) {
        return Optional.ofNullable(sensorRepository.findBySensorId(sensorId));
    }
}
