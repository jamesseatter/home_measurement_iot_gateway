package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.model.Measurement;
import eu.seatter.homeheating.edge.model.Sensor;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 13:45
 */
public interface MeasurementService extends CrudService<Measurement, Long>{

    List<Measurement> findAllBySensor(Sensor sensor);

}
