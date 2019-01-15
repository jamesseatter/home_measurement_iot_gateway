package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.model.Measurement;
import eu.seatter.homeheating.edge.model.Sensor;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 13:45
 */
public interface MeasurementService{

    Optional<List<Measurement>> getMeasurementBySensor(Sensor sensor);
}
