package eu.seatter.homemeasurement.gateway.repository;

import eu.seatter.homemeasurement.gateway.model.Device;
import eu.seatter.homemeasurement.gateway.model.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 31/12/2018
 * Time: 12:03
 */
@Repository
public interface SensorRepository extends CrudRepository<Sensor, Long> {

    /**
     * Finds device by using the sensor ID as a search criteria.
     * @param sensorId the ID of the sensor.
     * @return  A single sensor with the exact match of the ID provided.
     *          If no sensor is found, this method returns null.
     */
    Optional<Sensor> findBySensorId(String sensorId);

    /**
     * Finds all sensors by using the device as a search criteria.
     * @param device the unique ID of the sensor.
     * @return  A Set of sensors for the device.
     *          If no sensor is found, this method returns null.
     */
    Set<Sensor> findAllByDevice(Device device);
}
