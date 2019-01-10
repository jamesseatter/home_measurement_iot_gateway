package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.model.Device;
import eu.seatter.homeheating.edge.model.Measurement;
import eu.seatter.homeheating.edge.model.Sensor;
import eu.seatter.homeheating.edge.repository.MeasurementRepository;
import eu.seatter.homeheating.edge.repository.SensorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 13:57
 */
@RunWith(SpringRunner.class)
public class MeasurementServiceImplTest {

    @TestConfiguration
    static class MeasurementServiceImplTestConfiguration {
        @Bean
        public MeasurementService measurementService() {
            return new MeasurementServiceImpl();
        }

        @Bean
        public SensorService sensorService() {
            return new SensorServiceImpl();
        }
    }

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private SensorService sensorService;

    @MockBean
    private MeasurementRepository measurementRepository;

    @MockBean
    private SensorRepository sensorRepository;

    @Before
    public void setUp() throws Exception {
        Device device = new Device();
        device.setName("pi3n1");
        device.setManufacturer("pi");
        device.setSerialNo("abcdef11111");
        device.setOperatingSystem("raspberian");

        Sensor sensor = new Sensor();
        sensor.setDevice(device);
        sensor.setSensorId("123456");
        sensor.setSensorType("ONEWIRE");
        sensor.setValueType("TEMPERATURE");
        sensor.setValueUnit("CENTIGRADE");

        Measurement measurement = new Measurement();
        measurement.setSensor(sensor);
        measurement.setValue(20.0);

        Mockito.when(sensorRepository.findBySensorId(sensor.getSensorId())).thenReturn(sensor);
        Mockito.when(measurementRepository.findBySensor(measurement.getSensor())).thenReturn(Arrays.asList(measurement));

    }

    @Test
    public void whenValidateSensorID_thenMeasurementShouldBeFound() {
        String sensorID = "123456";
        Sensor sensorFound = sensorService.getSensorBySensorId(sensorID).orElse(new Sensor());

        List<Measurement> found = measurementService.getMeasurementBySensor(sensorFound).orElse(Collections.EMPTY_LIST);

        assertThat(found.size()).isEqualTo(1);
    }

    @Test
    public void whenValidateBadSensorID_thenMeasurementShouldNotBeFound() {
        String sensorId = "BADSENSORID";
        Sensor sensorFound = sensorService.getSensorBySensorId(sensorId).orElse(new Sensor());

        List<Measurement> found = measurementService.getMeasurementBySensor(sensorFound).orElse(Collections.EMPTY_LIST);

        assertThat(found.size()).isEqualTo(0);
    }
}