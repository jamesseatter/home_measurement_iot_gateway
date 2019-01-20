package eu.seatter.homeheating.edge.converters;

import eu.seatter.homeheating.edge.commands.MeasurementCommand;
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
 * Time: 11:29
 */
public class MeasurementToMeasurementCommandTest {

    private Long ID_VALUE = 1L;
    private Double VALUE_VALUE = 21.6D;
    private LocalDateTime MEASUREMENTTIME_VALUE = LocalDateTime.now(ZoneOffset.UTC);

    private String SENSORID_VALUE = "1927454";
    private SensorType SENSORTYPE_VALUE = SensorType.ONEWIRE;
    private SensorValueType VALUETYPE_VALUE = SensorValueType.TEMPERATURE;
    private SensorValueUnit VALUEUNIT_VALUE = SensorValueUnit.CELSIUS;
    private LocalDateTime DATEADDED_VALUE = LocalDateTime.now(ZoneOffset.UTC);
    private LocalDateTime DATEMODIFIED_VALUE = LocalDateTime.now(ZoneOffset.UTC);
    private Boolean ACTIVE_VALUE = true;

    private MeasurementToMeasurementCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new MeasurementToMeasurementCommand(new SensorToSensorCommand(new DeviceToDeviceCommand()));
    }

    @Test
    public void givenNothing_whenNullPassedToConvert_thenReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void givenNothing_whenEmptyObjectPassedToConvert_thenReturnNotNull() {
        assertNotNull(converter.convert(new Measurement()));
    }

    @Test
    public void givenSMeasurement_whenConvertedNullSensor_thenReturnMeasurementCommand() {
        //given
        Measurement measurement = new Measurement();
        measurement.setId(ID_VALUE);
        measurement.setMeasurementTime(MEASUREMENTTIME_VALUE);
        measurement.setValue(VALUE_VALUE);

        //when
        MeasurementCommand measurementCommand = converter.convert(measurement);

        //then
        assertNotNull(measurementCommand);
        assertNull(measurementCommand.getSensor());
        assertEquals(ID_VALUE, measurementCommand.getId());
        assertEquals(MEASUREMENTTIME_VALUE, measurementCommand.getMeasurementTime());
        assertEquals(VALUE_VALUE, measurementCommand.getValue());

    }

    @Test
    public void givenSMeasurement_whenConvertedWithSensor_thenReturnMeasurementCommand() {
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

        Measurement measurement = new Measurement();
        measurement.setId(ID_VALUE);
        measurement.setMeasurementTime(MEASUREMENTTIME_VALUE);
        measurement.setValue(VALUE_VALUE);
        measurement.setSensor(sensor);

        //when
        MeasurementCommand measurementCommand = converter.convert(measurement);

        //then
        assertNotNull(measurementCommand);
        assertNotNull(measurementCommand.getSensor());
        assertEquals(ID_VALUE, measurementCommand.getId());
        assertEquals(MEASUREMENTTIME_VALUE, measurementCommand.getMeasurementTime());
        assertEquals(VALUE_VALUE, measurementCommand.getValue());

    }
}