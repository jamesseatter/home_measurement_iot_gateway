package eu.seatter.homeheating.edge.repository;

import eu.seatter.homeheating.edge.model.Device;
import eu.seatter.homeheating.edge.model.Sensor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void whenFindById_thenReturnSensor() {
        //given
        Device device = new Device();
        device.setName("Dev1");
        device.setManufacturer("Pi");
        entityManager.persist(device);
        entityManager.flush();

        Sensor sensor = new Sensor();
        sensor.setSensorType("OneWire");
        sensor.setDevice(device);
        entityManager.persist(sensor);
        entityManager.flush();

        //when
        Sensor found = sensorRepository.findById(sensor.getId()).orElse(new Sensor());

        //then
        assertThat(found.getId()).isEqualTo(sensor.getId());
    }

}