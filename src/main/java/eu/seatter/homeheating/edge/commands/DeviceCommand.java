package eu.seatter.homeheating.edge.commands;

import eu.seatter.homeheating.edge.model.RegistrationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 01/01/2019
 * Time: 13:42
 */
@Setter
@Getter
@NoArgsConstructor
public class DeviceCommand{
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String uniqueId;
    @NotNull
    private String manufacturer;
    @NotNull
    private String operatingSystem;
    private RegistrationStatus registrationStatus;
    private String registrationCode;

}
