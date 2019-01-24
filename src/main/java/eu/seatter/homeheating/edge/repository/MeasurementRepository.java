package eu.seatter.homeheating.edge.repository;

import eu.seatter.homeheating.edge.model.Measurement;
import eu.seatter.homeheating.edge.model.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 30/12/2018
 * Time: 18:42
 */
@Repository
public interface MeasurementRepository extends CrudRepository<Measurement, Long> {

    /**
     * Finds measurements by using the sensor object as a search criteria.
     * @param sensor a sensor object
     * @return  A List of measurements for the provided sensor ID.
     *          If no measurements are found, this method returns an empty list.
     */
    Set<Measurement> findAllBySensor(Sensor sensor);
}
