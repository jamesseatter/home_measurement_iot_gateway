package eu.seatter.homeheating.edge.controller;


import eu.seatter.homeheating.edge.exceptions.DeviceNotFoundException;
import eu.seatter.homeheating.edge.model.Device;
import eu.seatter.homeheating.edge.model.Sensor;
import eu.seatter.homeheating.edge.service.DeviceService;
import eu.seatter.homeheating.edge.service.SensorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 21:32
 */

@RunWith(SpringRunner.class)
@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceService deviceService;

    @MockBean
    private SensorService sensorService;

    @Test
    public void whenGetDeviceById_thenShouldReturnDeviceAsJSON() throws Exception {
        //given
        Device device = new Device();
        device.setId(1L);
        device.setName("Dev1");
        device.setManufacturer("Pi");
        device.setUniqueId("12345");
        device.setOperatingSystem("Raspberian");

        given(deviceService.findById(anyLong())).willReturn(Optional.of(device));

        //when
        this.mockMvc.perform(get("/api/v1/device/{id}", device.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(device.getId()))
                .andExpect(jsonPath("$.uniqueId").value(device.getUniqueId()))
                .andExpect(jsonPath("$.manufacturer").value(device.getManufacturer()))
                .andExpect(jsonPath("$.name").value(device.getName()))
                .andExpect(jsonPath("$.operatingSystem").value(device.getOperatingSystem()));

        //then
        verify(deviceService, times(1)).findById(anyLong());
    }

    @Test
    public void whenGetDeviceByIdBADValue_thenShouldReturn404DeviceNotFoundAsJSON() throws Exception{
        //given
        Long badId = 99999999L;
        String message = "Device with ID " + badId + " not found";
        String detail = "Verify the ID is correct and the device is registered with the system.";
        when(deviceService.findById(anyLong())).thenThrow(new DeviceNotFoundException(message, detail));

        //when
        this.mockMvc.perform(get("/api/v1/device/{id}",badId))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(jsonPath("$.status" ).value("NOT_FOUND"))
                .andExpect(jsonPath("$.error_code" ).value("NOT_FOUND"))
                .andExpect(jsonPath("$.message" ).value(message))
                .andExpect(jsonPath("$.detail" ).value(detail));

        //then

    }

    @Test
    public void whenGetDeviceAll_thenShouldReturnDeviceListAsJSON() throws Exception {
        //given
        Set<Device> deviceSet = new HashSet<>();
        Device device = new Device();
        device.setId(1L);
        device.setName("Dev1");
        device.setManufacturer("Pi");
        device.setUniqueId("12345");
        device.setOperatingSystem("Raspberian");

        Device device2 = new Device();
        device2.setId(2L);
        device2.setName("Dev2");
        device2.setManufacturer("Pi");
        device2.setUniqueId("22334455");
        device2.setOperatingSystem("Raspberian");

        deviceSet.add(device);
        deviceSet.add(device2);

        given(deviceService.findAll()).willReturn(deviceSet);

        //when
        this.mockMvc.perform(get("/api/v1/devices").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].uniqueId").value(device.getUniqueId()))
                .andExpect(jsonPath("$[0].manufacturer").value(device.getManufacturer()))
                .andExpect(jsonPath("$[0].name").value(device.getName()))
                .andExpect(jsonPath("$[0].operatingSystem").value(device.getOperatingSystem()))
                .andExpect(jsonPath("$[1].uniqueId").value(device2.getUniqueId()))
                .andExpect(jsonPath("$[1].manufacturer").value(device2.getManufacturer()))
                .andExpect(jsonPath("$[1].name").value(device2.getName()))
                .andExpect(jsonPath("$[1].operatingSystem").value(device2.getOperatingSystem()));

        //then
        verify(deviceService, times(1)).findAll();
    }

    @Test
    public void whenGetDeviceByName_thenShouldReturnDeviceAsJSON() throws Exception {
        //given
        Device device = new Device();
        device.setName("Dev1");
        device.setManufacturer("Pi");
        device.setUniqueId("12345");
        device.setOperatingSystem("Raspberian");

        given(deviceService.findByName(device.getName())).willReturn(Optional.of(device));

        //when
        this.mockMvc.perform(get("/api/v1/device").param("name",device.getName()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.uniqueId").value(device.getUniqueId()))
                .andExpect(jsonPath("$.manufacturer").value(device.getManufacturer()))
                .andExpect(jsonPath("$.name").value(device.getName()))
                .andExpect(jsonPath("$.operatingSystem").value(device.getOperatingSystem()));

        //then
        verify(deviceService, times(1)).findByName(anyString());
    }

    @Test
    public void whenGetDeviceByNameBADValue_thenShouldReturn404DeviceNotFoundAsJSON() throws Exception{
        //given
        String badName = "BADNAME";
        String message = "Device with Name " + badName + " not found";
        String detail = "Verify the Serial Number is correct and the device is registered with the system.";
        when(deviceService.findByName(anyString())).thenThrow(new DeviceNotFoundException(message, detail));

        //when
        this.mockMvc.perform(get("/api/v1/device").param("name",badName).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(jsonPath("$.status" ).value("NOT_FOUND"))
                .andExpect(jsonPath("$.error_code" ).value("NOT_FOUND"))
                .andExpect(jsonPath("$.message" ).value(message))
                .andExpect(jsonPath("$.detail" ).value(detail));

        //then

    }

    @Test
    public void whenGetDeviceByUniqueID_thenShouldReturnDeviceAsJSON() throws Exception {
        //given
        Device device = new Device();
        device.setName("Dev1");
        device.setManufacturer("Pi");
        device.setUniqueId("12345");
        device.setOperatingSystem("Raspberian");

        given(deviceService.findByUniqueId(device.getUniqueId())).willReturn(Optional.of(device));

        //when
        this.mockMvc.perform(get("/api/v1/device").param("uniqueid", device.getUniqueId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.uniqueId").value(device.getUniqueId()))
                .andExpect(jsonPath("$.manufacturer").value(device.getManufacturer()))
                .andExpect(jsonPath("$.name").value(device.getName()))
                .andExpect(jsonPath("$.operatingSystem").value(device.getOperatingSystem()));

        //then
        verify(deviceService, times(1)).findByUniqueId(anyString());
    }

    @Test
    public void whenGetDeviceByUniqueIDBADValue_thenShouldReturn404DeviceNotFoundAsJSON() throws Exception{
        //given
        String uniqueId = "999999999";
        String message = "Device with Uniqie ID " + uniqueId + " not found";
        String detail = "Verify the Unique ID is correct and the device is registered with the system.";
        when(deviceService.findByUniqueId(anyString())).thenThrow(new DeviceNotFoundException(message, detail));

        //when
        this.mockMvc.perform(get("/api/v1/device/").param("uniqueid",uniqueId))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(jsonPath("$.status" ).value("NOT_FOUND"))
                .andExpect(jsonPath("$.error_code" ).value("NOT_FOUND"))
                .andExpect(jsonPath("$.message" ).value(message))
                .andExpect(jsonPath("$.detail" ).value(detail));

        //then
        verify(deviceService, times(1)).findByUniqueId(anyString());
    }

    @Test
    public void whenGetDeviceSensorsByDeviceID_thenShouldReturnSensorListAsJSON() throws Exception {
        //given

        Device device = new Device();
        device.setId(1L);
        device.setName("Dev1");
        device.setManufacturer("Pi");
        device.setUniqueId("12345");
        device.setOperatingSystem("Raspberian");

        Set<Sensor> sensorSet = new HashSet<>();
        Sensor sensor1 = new Sensor();
        sensor1.setId(1L);
        sensor1.setSensorId("9764423");

        Sensor sensor2 = new Sensor();
        sensor2.setId(2L);
        sensor2.setSensorId("124421");

        sensorSet.add(sensor1);
        sensorSet.add(sensor2);

        device.getSensors().add(sensor1);
        device.getSensors().add(sensor2);

        when(deviceService.findById(anyLong())).thenReturn(Optional.of(device));
        when(sensorService.findAllByDevice(any(Device.class))).thenReturn(sensorSet);

        //when
        this.mockMvc.perform(get("/api/v1/device/{id}/sensors",device.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].sensorId").value("9764423"))
                .andExpect(jsonPath("$[1].sensorId").value("124421"));


    }
}