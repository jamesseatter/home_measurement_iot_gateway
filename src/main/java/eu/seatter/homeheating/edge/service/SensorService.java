package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.model.*;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 12:38
 */
public interface SensorService extends CrudService<Sensor, Long>{

    Optional<Sensor> findBySensorId(String sensorId);
    Sensor addSensor(String sensorId, SensorType sensorType, SensorValueType valueType, SensorValueUnit valueUnit, boolean active, Device device);
}

