package eu.seatter.homeheating.edge.repository;

import eu.seatter.homeheating.edge.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 01/01/2019
 * Time: 13:44
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    /**
     * Finds device by using the device name as a search criteria.
     * @param deviceName the name of the device
     * @return  A single device with the exact match of the name provided.
     *          If no device is found, this method returns null.
     */
    Device findByName(String deviceName);

    /**
     * Finds single device by using the device serial number as a search criteria.
     * @param serialNumber the serial number of the device
     * @return  A single device with the exact match of the serial number provided.
     *          If no device is found, this method returns null.
     */
    Device findBySerialNo(String serialNumber);
}
