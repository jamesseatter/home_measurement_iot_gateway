package eu.seatter.homeheating.edge.repository;

import eu.seatter.homeheating.edge.model.Sensor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 31/12/2018
 * Time: 12:03
 */
public interface SensorRepository extends CrudRepository<Sensor, Long> {
}
