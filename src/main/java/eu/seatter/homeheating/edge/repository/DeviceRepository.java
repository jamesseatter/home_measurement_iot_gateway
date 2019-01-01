package eu.seatter.homeheating.edge.repository;

import eu.seatter.homeheating.edge.model.Device;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 01/01/2019
 * Time: 13:44
 */
public interface DeviceRepository extends CrudRepository<Device, Long> {
}
