package eu.seatter.homemeasurement.gateway.converters;

import eu.seatter.homemeasurement.gateway.commands.DeviceCommand;
import eu.seatter.homemeasurement.gateway.commands.SensorCommand;
import eu.seatter.homemeasurement.gateway.model.Sensor;
import eu.seatter.homemeasurement.gateway.model.SensorType;
import eu.seatter.homemeasurement.gateway.model.SensorValueType;
import eu.seatter.homemeasurement.gateway.model.SensorValueUnit;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 19/01/2019
 * Time: 10:10
 */
public class SensorCommandToSensorTest {
    private Long ID_VALUE = 1L;
    private String SENSORID_VALUE = "1927454";
    private SensorType SENSORTYPE_VALUE = SensorType.ONEWIRE;
    private SensorValueType VALUETYPE_VALUE = SensorValueType.TEMPERATURE;
    private SensorValueUnit VALUEUNIT_VALUE = SensorValueUnit.CELSIUS;
    private LocalDateTime DATEADDED_VALUE = LocalDateTime.now(ZoneOffset.UTC);
    private LocalDateTime DATEMODIFIED_VALUE = LocalDateTime.now(ZoneOffset.UTC);
    private Boolean ACTIVE_VALUE = true;

    private static final String NAME_VALUE = "pi3n1";
    private static final String UNIQUEID_VALUE = "dd7625242";
    private static final String MANUFACTURER_VALUE = "pi";
    private static final String OPERATINGSYSTEM_VALUE = "Raspberian";

    private SensorCommandToSensor converter;

    @Before
    public void setUp() {
        converter = new SensorCommandToSensor(new DeviceCommandToDevice());
    }

    @Test
    public void givenNothing_whenNullPassedToConvert_thenReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void givenNothing_whenEmptyObjectPassedToConvert_thenReturnNotNull() {
        assertNotNull(converter.convert(new SensorCommand()));
    }

    @Test
    public void givenSensorCommand_whenConvertedNullDevice_thenReturnSenor() {
        //given
        SensorCommand sensorCommand = new SensorCommand();
        sensorCommand.setId(ID_VALUE);
        sensorCommand.setSensorId(SENSORID_VALUE);
        sensorCommand.setSensorType(SENSORTYPE_VALUE);
        sensorCommand.setValueType(VALUETYPE_VALUE);
        sensorCommand.setValueUnit(VALUEUNIT_VALUE);
        sensorCommand.setDateAdded(DATEADDED_VALUE);
        sensorCommand.setDateModified(DATEMODIFIED_VALUE);
        sensorCommand.setActive(ACTIVE_VALUE);
        sensorCommand.setDevice(null);

        //when
        Sensor sensor = converter.convert(sensorCommand);

        //then
        assertNotNull(sensor);
        assertNull(sensor.getDevice());
        assertEquals(ID_VALUE, sensor.getId());
        assertEquals(SENSORID_VALUE, sensor.getSensorId());
        assertEquals(SENSORTYPE_VALUE, sensor.getSensorType());
        assertEquals(VALUETYPE_VALUE, sensor.getValueType());
        assertEquals(VALUEUNIT_VALUE, sensor.getValueUnit());
        assertEquals(DATEADDED_VALUE, sensor.getDateAdded());
        assertEquals(DATEMODIFIED_VALUE, sensor.getDateModified());
        assertEquals(ACTIVE_VALUE, sensor.getActive());

    }

    @Test
    public void givenSensorCommand_whenConvertedWithDevice_thenReturnSenor() {
        //given
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setId(ID_VALUE);
        deviceCommand.setUniqueId(UNIQUEID_VALUE);
        deviceCommand.setName(NAME_VALUE);
        deviceCommand.setManufacturer(MANUFACTURER_VALUE);
        deviceCommand.setOperatingSystem(OPERATINGSYSTEM_VALUE);

        SensorCommand sensorCommand = new SensorCommand();
        sensorCommand.setId(ID_VALUE);
        sensorCommand.setSensorId(SENSORID_VALUE);
        sensorCommand.setSensorType(SENSORTYPE_VALUE);
        sensorCommand.setValueType(VALUETYPE_VALUE);
        sensorCommand.setValueUnit(VALUEUNIT_VALUE);
        sensorCommand.setDateAdded(DATEADDED_VALUE);
        sensorCommand.setDateModified(DATEMODIFIED_VALUE);
        sensorCommand.setActive(ACTIVE_VALUE);
        sensorCommand.setDevice(null);
        sensorCommand.setDevice(deviceCommand);

        //when
        Sensor sensor = converter.convert(sensorCommand);

        //then
        assertNotNull(sensor);
        assertNotNull(sensor.getDevice());
        assertEquals(ID_VALUE, sensor.getId());
        assertEquals(SENSORID_VALUE, sensor.getSensorId());
        assertEquals(SENSORTYPE_VALUE, sensor.getSensorType());
        assertEquals(VALUETYPE_VALUE, sensor.getValueType());
        assertEquals(VALUEUNIT_VALUE, sensor.getValueUnit());
        assertEquals(DATEADDED_VALUE, sensor.getDateAdded());
        assertEquals(DATEMODIFIED_VALUE, sensor.getDateModified());
        assertEquals(ACTIVE_VALUE, sensor.getActive());

    }
}