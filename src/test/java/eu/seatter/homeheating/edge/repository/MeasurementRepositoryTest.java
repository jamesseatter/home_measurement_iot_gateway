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
 * Time: 13:37
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class MeasurementRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MeasurementRepository measurementRepository;

    private Device device;
    private Sensor sensor;
    private Measurement measurement;

    @Before
    public void setup() {
        device = new Device();
        device.setName("Dev1");
        device.setManufacturer("Pi");
        device.setSerialNo("12345");
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

        measurement = new Measurement();
        measurement.setValue(10.0);
        measurement.setSensor(sensor);
        measurement.setMeasurementTime(LocalDateTime.now(ZoneOffset.UTC));
        entityManager.persist(measurement);
        entityManager.flush();
    }

    @Test
    public void whenFindById_thenReturnMeasurement() {
        //given

        //when
        Measurement found = measurementRepository.findById(measurement.getId()).orElse(new Measurement());

        //then
        assertThat(found.getId()).isEqualTo(measurement.getId());
    }

}