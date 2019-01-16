package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.model.*;
import eu.seatter.homeheating.edge.repository.SensorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 12:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorServiceImplTest {

    @Autowired
    private SensorServiceImpl sensorService;

    @MockBean
    private SensorRepository sensorRepository;

    private Device returnedDevice;

    private Sensor returnedSensor;

    @Before
    public void setUp(){
        returnedDevice = Device.builder().name("pi3n1").manufacturer("pi").serialNo("112233").operatingSystem("raspberian").build();
        returnedSensor = Sensor.builder().sensorId("123456").sensorType(SensorType.ONEWIRE).valueType(SensorValueType.TEMPERATURE).valueUnit(SensorValueUnit.CELSIUS).device(returnedDevice).build();
    }

    @Test
    public void whenFindBySensorID_thenSensorShouldBeFound() {
        //given
        when(sensorRepository.findBySensorId(anyString())).thenReturn(Optional.of(returnedSensor));

        //when
        Sensor foundSensor = sensorService.findBySensorId(returnedSensor.getSensorId()).orElse(new Sensor());

        //then
        assertThat(foundSensor.getSensorId()).isEqualTo(returnedSensor.getSensorId());
    }

    @Test
    public void whenFindBySensorIDBADID_thenSensorShouldNotBeFound() {
        //given
        when(sensorRepository.findBySensorId(anyString())).thenReturn(Optional.empty());

        //when
        Sensor foundSensor = sensorService.findBySensorId("BADID").orElse(new Sensor());

        //then
        assertThat(foundSensor.getSensorId()).isNull();
    }

    @Test
    public void whenFindAll_thenSensorsShouldBeFound() {
        //given
        Set<Sensor> sensorSet = new HashSet<>();
        sensorSet.add(Sensor.builder().sensorId("123456").sensorType(SensorType.ONEWIRE).valueType(SensorValueType.TEMPERATURE).valueUnit(SensorValueUnit.CELSIUS).device(returnedDevice).build());
        sensorSet.add(Sensor.builder().sensorId("998877").sensorType(SensorType.ONEWIRE).valueType(SensorValueType.TEMPERATURE).valueUnit(SensorValueUnit.CELSIUS).device(returnedDevice).build());
        when(sensorRepository.findAll()).thenReturn(sensorSet);

        //when
        Set<Sensor> foundSensors = sensorService.findAll();

        //then
        assertThat(foundSensors.size()).isEqualTo(2);
    }

    @Test
    public void whenFindById_thenSensorShouldBeReturned() {
        //given
        when(sensorRepository.findById(anyLong())).thenReturn(Optional.of(returnedSensor));

        //when
        Sensor foundSensor = sensorService.findById(returnedSensor.getId()).orElse(new Sensor());

        //then
        assertThat(foundSensor.getId()).isEqualTo(returnedSensor.getId());
    }

    @Test
    public void whenSave_thenReturnSensor() {
        //given
        when(sensorRepository.save(any())).thenReturn(returnedSensor);

        //when
        Sensor savedSensor = sensorService.save(returnedSensor).orElse(new Sensor());

        //then
        assertThat(savedSensor.getSensorId()).isEqualTo(returnedSensor.getSensorId());
        verify(sensorRepository).save(any());
    }

    @Test
    public void whenDelete_thenReturnSensor() {
        //given

        //when
        sensorRepository.delete(returnedSensor);

        //then
        assertThat(sensorService.findAll().size()).isEqualTo(0);
        verify(sensorRepository).delete(any());
    }

    @Test
    public void whenDeleteById_thenReturnSensor() {
        //given

        //when
        sensorRepository.deleteById(returnedSensor.getId());

        //then
        assertThat(sensorService.findAll().size()).isEqualTo(0);
        verify(sensorRepository).deleteById(any());
    }

}