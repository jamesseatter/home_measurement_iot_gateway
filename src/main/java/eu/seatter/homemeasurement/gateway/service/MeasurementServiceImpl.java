package eu.seatter.homemeasurement.gateway.service;

import eu.seatter.homemeasurement.gateway.model.Measurement;
import eu.seatter.homemeasurement.gateway.model.Sensor;
import eu.seatter.homemeasurement.gateway.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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

    @Override
    @Transactional
    public Set<Measurement> findAllBySensor(Sensor sensor) {
        return measurementRepository.findAllBySensor(sensor);
    }

    @Override
    @Transactional
    public Set<Measurement> findAll() {
        Set<Measurement> measurementSet = new HashSet<>();
        measurementRepository.findAll().forEach(measurementSet::add);
        return measurementSet;
    }

    @Override
    @Transactional
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
