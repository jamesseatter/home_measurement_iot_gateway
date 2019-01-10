package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.model.Sensor;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 12:38
 */
public interface SensorService {

    Optional<Sensor> getSensorBySensorId(String sensorId);
}
