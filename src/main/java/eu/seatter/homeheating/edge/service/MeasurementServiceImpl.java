package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.model.Measurement;
import eu.seatter.homeheating.edge.model.Sensor;
import eu.seatter.homeheating.edge.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 13:45
 */
@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementServiceImpl(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> findAllBySensor(Sensor sensor) {
        return measurementRepository.findAllBySensor(sensor);
    }

    @Override
    public Set<Measurement> findAll() {
        Set<Measurement> measurementSet = new HashSet<>();
        measurementRepository.findAll().forEach(measurementSet::add);
        return measurementSet;
    }

    @Override
    public Optional<Measurement> findById(Long id) {
        return measurementRepository.findById(id);
    }

    @Override
    public Optional<Measurement> save(Measurement object) {
        return Optional.ofNullable(measurementRepository.save(object));
    }

    @Override
    public void delete(Measurement object) {
        measurementRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        measurementRepository.deleteById(id);

    }
}
