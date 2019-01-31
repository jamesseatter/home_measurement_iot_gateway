package eu.seatter.homeheating.edge.repository;

import eu.seatter.homeheating.edge.model.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 01/01/2019
 * Time: 13:44
 */
@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {

    /**
     * Finds device by using the device name as a search criteria.
     * @param deviceName the name of the device
     * @return  A single device with the exact match of the name provided.
     *          If no device is found, this method returns null.
     */
    Optional<Device> findByName(String deviceName);

    /**
     * Finds single device by using the device unique id as a search criteria.
     * @param uniqueId the unique ID of the device
     * @return  A single device with the exact match of the unique id provided.
     *          If no device is found, this method returns null.
     */
    Optional<Device> findByUniqueId(String uniqueId);

    /**
     * Finds single device by its registration code.
     * @param code the devices registration code
     * @return A single device with the exact match of the registration code provided.
     *          If no device is found, this method returns null.
     */
    Optional<Device> findByRegistrationCode(String code);
}
