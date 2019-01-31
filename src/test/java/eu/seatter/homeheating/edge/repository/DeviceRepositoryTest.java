package eu.seatter.homeheating.edge.repository;

import eu.seatter.homeheating.edge.model.Device;
import org.junit.Before;
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
 * Time: 12:58
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class DeviceRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DeviceRepository deviceRepository;

    private Device device;

    @Before
    public void setup() {
        //given
        device = new Device();
        device.setName("Dev1");
        device.setManufacturer("Pi");
        device.setUniqueId("12345");
        device.setOperatingSystem("Raspberian");
        entityManager.persist(device);
        entityManager.flush();
    }

    @Test
    public void whenFindByName_thenReturnDevice() {
        //given

        //when
        Device found = deviceRepository.findByName(device.getName()).orElse(new Device());

        //then
        assertThat(found.getName()).isEqualTo(device.getName());


    }

    @Test
    public void whenFindById_thenReturnDevice() {
        //given

        //when
        Device found = deviceRepository.findById(device.getId()).orElse(new Device());

        //then
        assertThat(found.getId()).isEqualTo(device.getId());
    }

 }