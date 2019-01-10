package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.model.Device;
import eu.seatter.homeheating.edge.model.Sensor;
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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 12:52
 */
@RunWith(SpringRunner.class)
public class SensorServiceImplTest {

    @TestConfiguration
    static class SensorServiceImplTestContextConfiguration {
        @Bean
        public SensorService sensorService() {
            return new SensorServiceImpl();
        }
    }

    @Autowired
    private SensorService sensorService;

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
        sensor.setSensorId("123456");
        sensor.setSensorType("ONEWIRE");
        sensor.setValueType("TEMPERATURE");
        sensor.setValueUnit("CENTIGRADE");
        sensor.setDevice(device);

        Mockito.when(sensorRepository.findBySensorId(sensor.getSensorId())).thenReturn(sensor);
    }

    @Test
    public void whenValidateSensorID_thenSensorShouldBeFound() {
        String sensorID = "123456";
        Sensor found = sensorService.getSensorBySensorId(sensorID).orElse(new Sensor());

        assertThat(found.getSensorId()).isEqualTo(sensorID);
    }

    @Test
    public void whenValidateBadSensorID_thenSensorShouldNotBeFound() {
        String sensorID = "BADID";
        Sensor found = sensorService.getSensorBySensorId(sensorID).orElse(new Sensor());

        assertThat(found.getSensorId()).isNull();
    }
}