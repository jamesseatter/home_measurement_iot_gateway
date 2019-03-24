package eu.seatter.homemeasurement.gateway.service;

import eu.seatter.homemeasurement.gateway.model.Measurement;
import eu.seatter.homemeasurement.gateway.model.Sensor;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 13:45
 */
public interface MeasurementService extends CrudService<Measurement, Long>{

    Set<Measurement> findAllBySensor(Sensor sensor);
}
