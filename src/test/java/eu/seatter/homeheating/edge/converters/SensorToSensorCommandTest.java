package eu.seatter.homeheating.edge.converters;

import eu.seatter.homeheating.edge.commands.SensorCommand;
import eu.seatter.homeheating.edge.model.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 19/01/2019
 * Time: 10:09
 */
public class SensorToSensorCommandTest {
    private Long ID_VALUE = 1L;
    private String SENSORID_VALUE = "1927454";
    private SensorType SENSORTYPE_VALUE = SensorType.ONEWIRE;
    private SensorValueType VALUETYPE_VALUE = SensorValueType.TEMPERATURE;
    private SensorValueUnit VALUEUNIT_VALUE = SensorValueUnit.CELSIUS;
    private LocalDateTime DATEADDED_VALUE = LocalDateTime.now(ZoneOffset.UTC);
    private LocalDateTime DATEMODIFIED_VALUE = LocalDateTime.now(ZoneOffset.UTC);
    private Boolean ACTIVE_VALUE = true;

    private static final String NAME_VALUE = "pi3n1";
    private static final String SERIALNO_VALUE = "dd7625242";
    private static final String MANUFACTURER_VALUE = "pi";
    private static final String OPERATINGSYSTEM_VALUE = "Raspberian";

    private SensorToSensorCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new SensorToSensorCommand(new DeviceToDeviceCommand());
    }

    @Test
    public void givenNothing_whenNullPassedToConvert_thenReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void givenNothing_whenEmptyObjectPassedToConvert_thenReturnNotNull() {
        assertNotNull(converter.convert(new Sensor()));
    }

    @Test
    public void givenSensor_whenConvertedNullDevice_thenReturnSensorCommand() {
        //given
        Sensor sensor = new Sensor();
        sensor.setId(ID_VALUE);
        sensor.setSensorId(SENSORID_VALUE);
        sensor.setSensorType(SENSORTYPE_VALUE);
        sensor.setValueType(VALUETYPE_VALUE);
        sensor.setValueUnit(VALUEUNIT_VALUE);
        sensor.setDateAdded(DATEADDED_VALUE);
        sensor.setDateModified(DATEMODIFIED_VALUE);
        sensor.setActive(ACTIVE_VALUE);
        sensor.setDevice(null);

        //when
        SensorCommand sensorCommand = converter.convert(sensor);

        //then
        assertNotNull(sensorCommand);
        assertNull(sensorCommand.getDevice());
        assertEquals(ID_VALUE, sensorCommand.getId());
        assertEquals(SENSORID_VALUE, sensorCommand.getSensorId());
        assertEquals(SENSORTYPE_VALUE, sensorCommand.getSensorType());
        assertEquals(VALUETYPE_VALUE, sensorCommand.getValueType());
        assertEquals(VALUEUNIT_VALUE, sensorCommand.getValueUnit());
        assertEquals(DATEADDED_VALUE, sensorCommand.getDateAdded());
        assertEquals(DATEMODIFIED_VALUE, sensorCommand.getDateModified());
        assertEquals(ACTIVE_VALUE, sensorCommand.getActive());

    }

    @Test
    public void givenSensor_whenConvertedWithDevice_thenReturnSensorCommand() {
        //given
        Sensor sensor = new Sensor();
        sensor.setId(ID_VALUE);
        sensor.setSensorId(SENSORID_VALUE);
        sensor.setSensorType(SENSORTYPE_VALUE);
        sensor.setValueType(VALUETYPE_VALUE);
        sensor.setValueUnit(VALUEUNIT_VALUE);
        sensor.setDateAdded(DATEADDED_VALUE);
        sensor.setDateModified(DATEMODIFIED_VALUE);
        sensor.setActive(ACTIVE_VALUE);

        Device device = new Device();
        device.setId(ID_VALUE);
        device.setSerialNo(SERIALNO_VALUE);
        device.setName(NAME_VALUE);
        device.setManufacturer(MANUFACTURER_VALUE);
        device.setOperatingSystem(OPERATINGSYSTEM_VALUE);

        sensor.setDevice(device);

        //when
        SensorCommand sensorCommand = converter.convert(sensor);

        //then
        assertNotNull(sensor);
        assertNotNull(sensorCommand.getDevice());
        assertEquals(ID_VALUE, sensorCommand.getId());
        assertEquals(SENSORID_VALUE, sensorCommand.getSensorId());
        assertEquals(SENSORTYPE_VALUE, sensorCommand.getSensorType());
        assertEquals(VALUETYPE_VALUE, sensorCommand.getValueType());
        assertEquals(VALUEUNIT_VALUE, sensorCommand.getValueUnit());
        assertEquals(DATEADDED_VALUE, sensorCommand.getDateAdded());
        assertEquals(DATEMODIFIED_VALUE, sensorCommand.getDateModified());
        assertEquals(ACTIVE_VALUE, sensorCommand.getActive());

    }
}