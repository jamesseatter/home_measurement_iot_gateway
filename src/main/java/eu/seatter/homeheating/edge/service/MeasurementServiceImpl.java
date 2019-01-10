package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.model.Measurement;
import eu.seatter.homeheating.edge.model.Sensor;
import eu.seatter.homeheating.edge.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 13:45
 */
@Service
public class MeasurementServiceImpl implements MeasurementService {

    @Autowired
    private MeasurementRepository repository;

    @Override
    public Optional<List<Measurement>> getMeasurementBySensor(Sensor sensor) {
        return Optional.ofNullable(repository.findBySensor(sensor));
    }
}
