package eu.seatter.homeheating.edge.repository;

import eu.seatter.homeheating.edge.model.Device;
import eu.seatter.homeheating.edge.model.Sensor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 08/01/2019
 * Time: 12:58
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class DeviceRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    public void whenFindByName_thenReturnDevice() {
        //given
        Device device = new Device();
        device.setName("pi3n1");

        entityManager.persist(device);
        entityManager.flush();

        //when
        Device found = deviceRepository.findByName(device.getName());

        //then
        assertThat(found.getName()).isEqualTo(device.getName());


    }

    @Test
    public void whenFindById_thenReturnDevice() {
        //given
        Device device = new Device();
        device.setName("pi3n1");

        entityManager.persist(device);
        entityManager.flush();

        //when
        Device found = deviceRepository.findById(device.getId()).orElse(new Device());

        //then
        assertThat(found.getId()).isEqualTo(device.getId());
    }

    @Test
    public void whenPersistWithSensors_thenReturnDevicewithSensors() {
        //given

        Sensor sensor = new Sensor();
        sensor.setSensorType("ONEWIRE");
        Set<Sensor> sensorList= new HashSet<>();
        sensorList.add(sensor);

        Device device = new Device();
        device.setName("pi3n1");
        device.setSensors(sensorList);

        entityManager.persist(device);
        entityManager.flush();

        //when
        Device found = deviceRepository.findById(device.getId()).orElse(new Device());

        //then
        assertThat(found.getSensors().size()).isEqualTo(1);
    }
}