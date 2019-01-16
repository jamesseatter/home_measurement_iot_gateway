package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.model.*;
import eu.seatter.homeheating.edge.repository.MeasurementRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 13:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasurementServiceImplTest {

    @Autowired
    private MeasurementServiceImpl measurementService;

    @MockBean
    private MeasurementRepository measurementRepository;

    private Device returnedDevice;

    private Sensor returnedSensor;

    private Measurement returnedMeasurement;

    @Before
    public void setUp() {
        returnedDevice = Device.builder().name("pi3n1").manufacturer("pi").serialNo("112233").operatingSystem("raspberian").build();
        returnedSensor = Sensor.builder().sensorId("123456").sensorType(SensorType.ONEWIRE).valueType(SensorValueType.TEMPERATURE).valueUnit(SensorValueUnit.CELSIUS).device(returnedDevice).build();
        returnedMeasurement = Measurement.builder().sensor(returnedSensor).measurementTime(LocalDateTime.now(ZoneOffset.UTC)).value(20.0D).build();
    }

    @Test
    public void whenFindAllBySensor_thenMeasurementsShouldBeFound() {
        //given
        List<Measurement> measurementSet = new ArrayList<>();
        measurementSet.add(Measurement.builder().sensor(returnedSensor).measurementTime(LocalDateTime.now(ZoneOffset.UTC)).value(20.0D).build());
        measurementSet.add(Measurement.builder().sensor(returnedSensor).measurementTime(LocalDateTime.now(ZoneOffset.UTC)).value(21.0D).build());
        when(measurementRepository.findAllBySensor(any(Sensor.class))).thenReturn(measurementSet);

        //when
        List<Measurement> foundMeasurements = measurementService.findAllBySensor(returnedSensor);

        assertThat(foundMeasurements.size()).isEqualTo(2);
    }

    @Test
    public void whenFindAll_thenMeasurementsShouldBeFound() {
        //given
        Set<Measurement> measurementSet = new HashSet<>();
        measurementSet.add(Measurement.builder().sensor(returnedSensor).measurementTime(LocalDateTime.now(ZoneOffset.UTC)).value(20.0D).build());
        measurementSet.add(Measurement.builder().sensor(returnedSensor).measurementTime(LocalDateTime.now(ZoneOffset.UTC)).value(21.0D).build());
        when(measurementRepository.findAll()).thenReturn(measurementSet);

        //when
        Set<Measurement> foundMeasurements = measurementService.findAll();

        assertThat(foundMeasurements.size()).isEqualTo(2);
    }


    @Test
    public void whenFindById_thenSensorShouldBeReturned() {
        //given
        when(measurementRepository.findById(anyLong())).thenReturn(Optional.of(returnedMeasurement));

        //when
        Measurement foundMeasurement = measurementService.findById(returnedMeasurement.getId()).orElse(new Measurement());

        //then
        assertThat(foundMeasurement.getId()).isEqualTo(returnedMeasurement.getId());
    }

    @Test
    public void whenSave_thenReturnMeasurement() {
        //given
        when(measurementRepository.save(any())).thenReturn(returnedMeasurement);

        //when
        Measurement savedMeasurement = measurementService.save(returnedMeasurement).orElse(new Measurement());

        //then
        assertThat(savedMeasurement.getId()).isEqualTo(returnedMeasurement.getId());
        verify(measurementRepository).save(any());
    }

    @Test
    public void whenDelete_thenReturnMeasurement() {
        //given

        //when
        measurementRepository.delete(returnedMeasurement);

        //then
        assertThat(measurementService.findAll().size()).isEqualTo(0);
        verify(measurementRepository).delete(any());
    }

    @Test
    public void whenDeleteById_thenReturnMeasurement() {
        //given

        //when
        measurementRepository.deleteById(returnedMeasurement.getId());

        //then
        assertThat(measurementService.findAll().size()).isEqualTo(0);
        verify(measurementRepository).deleteById(any());
    }
}