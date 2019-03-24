package eu.seatter.homemeasurement.gateway.service;

import eu.seatter.homemeasurement.gateway.commands.RegistrationCommand;
import eu.seatter.homemeasurement.gateway.model.Device;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 25/01/2019
 * Time: 11:25
 */
public interface RegistrationService {
    Optional<Device> newDevice(RegistrationCommand registrationCommand);

    Optional<Device> getRegistration(String uniqueId);

}
