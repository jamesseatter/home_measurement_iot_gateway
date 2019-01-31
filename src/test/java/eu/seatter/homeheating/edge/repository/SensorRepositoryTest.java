package eu.seatter.homeheating.edge.repository;

import eu.seatter.homeheating.edge.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 08/01/2019
 * Time: 13:05
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SensorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SensorRepository sensorRepository;

    private Device device;
    private Sensor sensor;

    @Before
    public void setup() {
        device = new Device();
        device.setName("Dev1");
        device.setManufacturer("Pi");
        device.setUniqueId("12345");
        device.setOperatingSystem("Raspberian");
        entityManager.persist(device);
        entityManager.flush();

        sensor = new Sensor();
        sensor.setSensorId("id_1");
        sensor.setSensorType(SensorType.ONEWIRE);
        sensor.setDevice(device);
        sensor.setValueUnit(SensorValueUnit.CELSIUS);
        sensor.setValueType(SensorValueType.TEMPERATURE);
        sensor.setDateAdded(LocalDateTime.now((ZoneOffset.UTC)));
        sensor.setDateModified(LocalDateTime.now((ZoneOffset.UTC)));
        sensor.setActive(true);
        entityManager.persist(sensor);
        entityManager.flush();
    }


    @Test
    public void whenFindById_thenReturnSensor() {
        //given

        //when
        Sensor found = sensorRepository.findById(sensor.getId()).orElse(new Sensor());

        //then
        assertThat(found.getId()).isEqualTo(sensor.getId());
    }

}