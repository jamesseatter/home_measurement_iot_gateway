package eu.seatter.homeheating.edge.commands;

import eu.seatter.homeheating.edge.model.RegistrationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 27/01/2019
 * Time: 22:50
 */
@Setter
@Getter
@NoArgsConstructor
public class RegistrationCommand {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String serialNo;
    @NotNull
    private String manufacturer;
    @NotNull
    private String operatingSystem;
    private RegistrationStatus registrationStatus;
    private String registrationCode;
}
