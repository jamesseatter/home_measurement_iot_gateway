package eu.seatter.homeheating.edge.repository;

import eu.seatter.homeheating.edge.model.Measurement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 30/12/2018
 * Time: 18:42
 */
@Repository
public interface MeasurementRepository extends CrudRepository<Measurement, Long> {
}
