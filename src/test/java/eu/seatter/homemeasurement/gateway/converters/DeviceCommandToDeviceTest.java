package eu.seatter.homemeasurement.gateway.converters;

import eu.seatter.homemeasurement.gateway.commands.DeviceCommand;
import eu.seatter.homemeasurement.gateway.model.Device;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 18/01/2019
 * Time: 23:58
 */
public class DeviceCommandToDeviceTest {
    private static final Long ID_VALUE = 1L;
    private static final String NAME_VALUE = "pi3n1";
    private static final String UNIQUEID_VALUE = "dd7625242";
    private static final String MANUFACTURER_VALUE = "pi";
    private static final String OPERATINGSYSTEM_VALUE = "Raspberian";

    private DeviceCommandToDevice converter;

    @Before
    public void setUp(){
        converter = new DeviceCommandToDevice();
    }

    @Test
    public void givenNothing_whenNullPassedToConvert_thenReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void givenNothing_whenEmptyObjectPassedToConvert_thenReturnNotNull() {
        assertNotNull(converter.convert(new DeviceCommand()));
    }


    @Test
    public void givenDeviceCommand_whenConverted_thenReturnDevice() {
        //given
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setId(ID_VALUE);
        deviceCommand.setUniqueId(UNIQUEID_VALUE);
        deviceCommand.setName(NAME_VALUE);
        deviceCommand.setManufacturer(MANUFACTURER_VALUE);
        deviceCommand.setOperatingSystem(OPERATINGSYSTEM_VALUE);

        //when
        Device device = converter.convert(deviceCommand);

        //then
        assertNotNull(device);
        assertEquals(ID_VALUE, device.getId());
        assertEquals(UNIQUEID_VALUE, device.getUniqueId());
        assertEquals(NAME_VALUE, device.getName());
        assertEquals(MANUFACTURER_VALUE, device.getManufacturer());
        assertEquals(OPERATINGSYSTEM_VALUE, device.getOperatingSystem());
    }
}