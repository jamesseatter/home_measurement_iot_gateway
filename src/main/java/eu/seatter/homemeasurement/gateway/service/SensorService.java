package eu.seatter.homemeasurement.gateway.service;

import eu.seatter.homemeasurement.gateway.model.*;

import java.util.Optional;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 12:38
 */
public interface SensorService extends CrudService<Sensor, Long>{

    Optional<Sensor> findBySensorId(String sensorId);
    Sensor addSensor(String sensorId, SensorType sensorType, SensorValueType valueType, SensorValueUnit valueUnit, boolean active, Device device);
    Set<Sensor> findAllByDevice(Device device);
}

