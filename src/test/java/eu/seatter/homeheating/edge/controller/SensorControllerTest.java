//TODO Make this thing work. The controller works, but the tests do not.

//package eu.seatter.homeheating.edge.controller;
//
//import eu.seatter.homeheating.edge.converters.SensorToSensorCommand;
//import eu.seatter.homeheating.edge.model.Sensor;
//import eu.seatter.homeheating.edge.model.SensorType;
//import eu.seatter.homeheating.edge.model.SensorValueType;
//import eu.seatter.homeheating.edge.model.SensorValueUnit;
//import eu.seatter.homeheating.edge.service.SensorService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * Created by IntelliJ IDEA.
// * User: jas
// * Date: 20/01/2019
// * Time: 01:13
// */
//@RunWith(SpringRunner.class)
//@WebMvcTest(SensorController.class)
//@AutoConfigureMockMvc(secure=false)
//public class SensorControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    WebApplicationContext webApplicationContext;
//
//    @MockBean
//    private SensorService sensorServiceMock;
//
//    @MockBean
//    private SensorToSensorCommand sensorToSensorCommand;
//
//    private Sensor sensor;
//
//    @Before
//    public void setUp() throws Exception {
//        sensor = new Sensor();
//        sensor.setId(1L);
//        sensor.setSensorId("124421");
//        sensor.setSensorType(SensorType.ONEWIRE);
//        sensor.setValueType(SensorValueType.TEMPERATURE);
//        sensor.setValueUnit(SensorValueUnit.CELSIUS);
//        sensor.setDateAdded(LocalDateTime.now(ZoneOffset.UTC));
//        sensor.setDateModified(LocalDateTime.now(ZoneOffset.UTC));
//        sensor.setActive(true);
//        sensor.setDevice(null);
//    }
//
//    @Test
//    public void mockSetup() {
//        assertThat(this.sensor).isNotNull();
//        assertThat(this.sensorServiceMock).isNotNull();
//        assertThat(this.sensorToSensorCommand).isNotNull();
//    }
//
//    @Test
//    public void givenSensor_whenGetSensorById_thenShouldReturnSensorAsJSON() throws Exception {
//        //given
//        given(sensorServiceMock.findById2(sensor.getId())).willReturn(sensor);
//
//        //when
//        this.mockMvc.perform(get("api/v1/sensor/{id}/show",sensor.getId()).contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
////                .andExpect(jsonPath("$.sensorId").value(sensor.getSensorId()))
////                .andExpect(jsonPath("$.sensorType").value(sensor.getSensorType()))
////                .andExpect(jsonPath("$.valueType").value(sensor.getValueType()))
////                .andExpect(jsonPath("$.valueUnit").value(sensor.getValueUnit()))
////                .andExpect(jsonPath("$.dateAdded").value(sensor.getDateAdded()))
////                .andExpect(jsonPath("$.dateModified").value(sensor.getDateModified()))
////                .andExpect(jsonPath("$.active").value(sensor.getActive()))
////                .andExpect(jsonPath("$.device").isEmpty());
//
//        //then
//        verify(sensorServiceMock, times(1)).findById2(anyLong());
//    }
//
//}
