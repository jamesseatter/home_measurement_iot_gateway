package eu.seatter.homemeasurement.gateway.converters;

import eu.seatter.homemeasurement.gateway.commands.MeasurementCommand;
import eu.seatter.homemeasurement.gateway.commands.SensorCommand;
import eu.seatter.homemeasurement.gateway.model.Measurement;
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
 * Time: 11:29
 */
public class MeasurementCommandToMeasurementTest {
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

    private MeasurementCommandToMeasurement converter;

    @Before
    public void setUp() throws Exception {
        converter = new MeasurementCommandToMeasurement(new SensorCommandToSensor(new DeviceCommandToDevice()));
    }

    @Test
    public void givenNothing_whenNullPassedToConvert_thenReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void givenNothing_whenEmptyObjectPassedToConvert_thenReturnNotNull() {
        assertNotNull(converter.convert(new MeasurementCommand()));
    }

    @Test
    public void givenSMeasurementCommand_whenConvertedNullSensorCommand_thenReturnMeasurement() {
        //given
        MeasurementCommand measurementCommand = new MeasurementCommand();
        measurementCommand.setId(ID_VALUE);
        measurementCommand.setMeasurementTime(MEASUREMENTTIME_VALUE);
        measurementCommand.setValue(VALUE_VALUE);

        //when
        Measurement measurement = converter.convert(measurementCommand);

        //then
        assertNotNull(measurement);
        assertNull(measurement.getSensor());
        assertEquals(ID_VALUE, measurement.getId());
        assertEquals(MEASUREMENTTIME_VALUE, measurement.getMeasurementTime());
        assertEquals(VALUE_VALUE, measurement.getValue());

    }

    @Test
    public void givenSMeasurementCommand_whenConvertedWithSensorCommand_thenReturnMeasurement() {
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

        MeasurementCommand measurementCommand = new MeasurementCommand();
        measurementCommand.setId(ID_VALUE);
        measurementCommand.setMeasurementTime(MEASUREMENTTIME_VALUE);
        measurementCommand.setValue(VALUE_VALUE);
        measurementCommand.setSensor(sensorCommand);


        //when
        Measurement measurement = converter.convert(measurementCommand);

        //then
        assertNotNull(measurement);
        assertNotNull(measurement.getSensor());
        assertEquals(ID_VALUE, measurement.getId());
        assertEquals(MEASUREMENTTIME_VALUE, measurement.getMeasurementTime());
        assertEquals(VALUE_VALUE, measurement.getValue());

    }
}