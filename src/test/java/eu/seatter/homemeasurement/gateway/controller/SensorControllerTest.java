package eu.seatter.homemeasurement.gateway.controller;

import eu.seatter.homemeasurement.gateway.model.Sensor;
import eu.seatter.homemeasurement.gateway.model.SensorType;
import eu.seatter.homemeasurement.gateway.model.SensorValueType;
import eu.seatter.homemeasurement.gateway.model.SensorValueUnit;
import eu.seatter.homemeasurement.gateway.service.MeasurementService;
import eu.seatter.homemeasurement.gateway.service.SensorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 24/01/2019
 * Time: 08:37
 */
@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest(SensorController.class)
public class SensorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SensorService sensorService;

    @MockBean
    private MeasurementService measurementService;

    private Sensor sensor;

    @Before
    public void setUp() throws Exception {
        sensor = new Sensor();
        sensor.setId(1L);
        sensor.setSensorId("124421");
        sensor.setSensorType(SensorType.ONEWIRE);
        sensor.setValueType(SensorValueType.TEMPERATURE);
        sensor.setValueUnit(SensorValueUnit.CELSIUS);
        sensor.setDateAdded(LocalDateTime.now(ZoneOffset.UTC));
        sensor.setDateModified(LocalDateTime.now(ZoneOffset.UTC));
        sensor.setActive(true);
        sensor.setDevice(null);

    }


    @Test
    public void whenGetSensorAll_thenShouldReturnSensorListAsJSON() throws Exception {
        //given
        Sensor sensor1 = new Sensor();
        sensor1.setId(2L);
        sensor1.setSensorId("124421");
        sensor1.setSensorType(SensorType.ONEWIRE);
        sensor1.setValueType(SensorValueType.TEMPERATURE);
        sensor1.setValueUnit(SensorValueUnit.CELSIUS);
        sensor1.setDateAdded(LocalDateTime.now(ZoneOffset.UTC));
        sensor1.setDateModified(LocalDateTime.now(ZoneOffset.UTC));
        sensor1.setActive(true);
        sensor1.setDevice(null);

        Sensor sensor2 = new Sensor();
        sensor2.setId(3L);
        sensor2.setSensorId("124421");
        sensor2.setSensorType(SensorType.ONEWIRE);
        sensor2.setValueType(SensorValueType.TEMPERATURE);
        sensor2.setValueUnit(SensorValueUnit.CELSIUS);
        sensor2.setDateAdded(LocalDateTime.now(ZoneOffset.UTC));
        sensor2.setDateModified(LocalDateTime.now(ZoneOffset.UTC));
        sensor2.setActive(true);
        sensor2.setDevice(null);

        Set<Sensor> sensorSet = new HashSet<>();
        sensorSet.add(sensor1);
        sensorSet.add(sensor2);

        given(sensorService.findAll()).willReturn(sensorSet);

        //when
        this.mockMvc.perform(get("/api/v1/sensors/").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));

        //then
        verify(sensorService).findAll();
    }

//    @Test
//    public void whenSaveSensorsGoodJSONList_shouldReturnSensorCommandList() throws Exception{
//        //given
//        Sensor sensor1 = new Sensor();
//        sensor1.setId(2L);
//        sensor1.setSensorId("124421");
//        sensor1.setSensorType(SensorType.ONEWIRE);
//        sensor1.setValueType(SensorValueType.TEMPERATURE);
//        sensor1.setValueUnit(SensorValueUnit.CELSIUS);
//        sensor1.setDateAdded(LocalDateTime.now(ZoneOffset.UTC));
//        sensor1.setDateModified(LocalDateTime.now(ZoneOffset.UTC));
//        sensor1.setActive(true);
//        sensor1.setDevice(null);
//
//        Sensor sensor2 = new Sensor();
//        sensor2.setId(3L);
//        sensor2.setSensorId("124421");
//        sensor2.setSensorType(SensorType.ONEWIRE);
//        sensor2.setValueType(SensorValueType.TEMPERATURE);
//        sensor2.setValueUnit(SensorValueUnit.CELSIUS);
//        sensor2.setDateAdded(LocalDateTime.now(ZoneOffset.UTC));
//        sensor2.setDateModified(LocalDateTime.now(ZoneOffset.UTC));
//        sensor2.setActive(true);
//        sensor2.setDevice(null);
//
//        List<Sensor> sensorList = new ArrayList<>();
//        sensorList.add(sensor1);
//        sensorList.add(sensor2);
//
//        given(sensorService.saveAll(anyList())).willReturn(sensorList);
//        String json = UtilJSON.asJsonString(sensorList);
//
//        //when
//        this.mockMvc.perform(post("/api/v1/sensors/new")
//                    .contentType("application/json;charset=UTF-8")
//                    .content(json))
//                    .andExpect(status().isCreated())
//                    .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
//                    .andExpect(jsonPath("$", hasSize(2)));
//        //then
//        verify(sensorService).saveAll(anyList());
//
//    }


}

