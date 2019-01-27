package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.commands.RegistrationCommand;
import eu.seatter.homeheating.edge.model.Device;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 25/01/2019
 * Time: 11:25
 */
public interface RegistrationService {
    Optional<Device> newDevice(RegistrationCommand registrationCommand);

    Optional<Device> getRegistration(RegistrationCommand registrationCommand);

}
