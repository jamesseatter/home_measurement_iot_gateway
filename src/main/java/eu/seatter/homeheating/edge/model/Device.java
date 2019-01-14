package eu.seatter.homeheating.edge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 01/01/2019
 * Time: 13:42
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "device")
public class Device extends BaseEntity {

    public Device(@NotNull @Size(max = 30) String name, @NotNull @Size(max = 50) String serialNo, @NotNull @Size(max = 50) String manufacturer, @NotNull @Size(max = 50) String operatingSystem) {
        this.name = name;
        this.serialNo = serialNo;
        this.manufacturer = manufacturer;
        this.operatingSystem = operatingSystem;
    }

    @NotNull
    @Size(max = 30)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(max = 50)
    @Column(name = "serialno")
    private String serialNo;

    @NotNull
    @Size(max = 50)
    @Column(name = "manufacturer")
    private String manufacturer;

    @NotNull
    @Size(max = 50)
    @Column(name = "operatingsystem")
    private String operatingSystem;

}
