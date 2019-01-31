package eu.seatter.homeheating.edge.converters;

import eu.seatter.homeheating.edge.commands.DeviceCommand;
import eu.seatter.homeheating.edge.model.Device;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 18/01/2019
 * Time: 23:58
 */
public class DeviceToDeviceCommandTest {
    private static final Long ID_VALUE = 1L;
    private static final String NAME_VALUE = "pi3n1";
    private static final String UNIQUEID_VALUE = "dd7625242";
    private static final String MANUFACTURER_VALUE = "pi";
    private static final String OPERATINGSYSTEM_VALUE = "Raspberian";

    private DeviceToDeviceCommand converter;

    @Before
    public void setUp() {
        converter = new DeviceToDeviceCommand();
    }

    @Test
    public void givenNothing_whenNullPassedToConvert_thenReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void givenNothing_whenEmptyObjectPassedToConvert_thenReturnNotNull() {
        assertNotNull(converter.convert(new Device()));
    }

    @Test
    public void givenDevice_whenConverted_thenReturnDeviceCommand() {
        //given
        Device device = new Device();
        device.setId(ID_VALUE);
        device.setUniqueId(UNIQUEID_VALUE);
        device.setName(NAME_VALUE);
        device.setManufacturer(MANUFACTURER_VALUE);
        device.setOperatingSystem(OPERATINGSYSTEM_VALUE);

        //when
        DeviceCommand deviceCommand = converter.convert(device);

        //then
        assertNotNull(device);
        assertEquals(ID_VALUE, deviceCommand.getId());
        assertEquals(UNIQUEID_VALUE, deviceCommand.getUniqueId());
        assertEquals(NAME_VALUE, deviceCommand.getName());
        assertEquals(MANUFACTURER_VALUE, deviceCommand.getManufacturer());
        assertEquals(OPERATINGSYSTEM_VALUE, deviceCommand.getOperatingSystem());
    }
}