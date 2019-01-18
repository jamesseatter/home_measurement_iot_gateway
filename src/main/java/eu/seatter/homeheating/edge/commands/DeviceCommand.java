package eu.seatter.homeheating.edge.commands;

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
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String serialNo;
    @NotNull
    private String manufacturer;
    @NotNull
    private String operatingSystem;

}
