package eu.seatter.homeheating.edge.repository;

import eu.seatter.homeheating.edge.model.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 31/12/2018
 * Time: 12:03
 */
@Repository
public interface SensorRepository extends CrudRepository<Sensor, Long> {

    /**
     * Finds device by using the device name as a search criteria.
     * @param sensorId the unique ID of the sensor.
     * @return  A single sensor with the exact match of the ID provided.
     *          If no sensor is found, this method returns null.
     */
    Sensor findBySensorId(String sensorId);
}
