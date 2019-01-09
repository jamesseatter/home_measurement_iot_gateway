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
}
