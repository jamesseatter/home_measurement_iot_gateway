package eu.seatter.homeheating.edge.controller;


import eu.seatter.homeheating.edge.exceptions.DeviceNotFoundException;
import eu.seatter.homeheating.edge.model.Device;
import eu.seatter.homeheating.edge.service.DeviceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

    @Test
    public void whenGetDeviceBySerialNo_thenShouldReturnDeviceAsJSON() throws Exception {
        //given
        Device device = new Device();
        device.setName("Dev1");
        device.setManufacturer("Pi");
        device.setSerialNo("12345");
        device.setOperatingSystem("Raspberian");

        given(deviceService.findBySerialNo(device.getSerialNo())).willReturn(Optional.of(device));

        //then
        this.mockMvc.perform(get("/api/v1/device/{serialno}", device.getSerialNo()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.serialNo").value(device.getSerialNo()))
                .andExpect(jsonPath("$.manufacturer").value(device.getManufacturer()))
                .andExpect(jsonPath("$.name").value(device.getName()))
                .andExpect(jsonPath("$.operatingSystem").value(device.getOperatingSystem()));

        //when
        verify(deviceService, VerificationModeFactory.times(1)).findBySerialNo(anyString());
    }

    @Test
    public void whenGetDeviceByBADSerialNo_thenShouldReturn404DeviceNotFoundAsJSON() throws Exception{
        //given
        String serialNo = "999999999";
        String message = "Device with SN " + serialNo + " not found";
        String detail = "Verify the Serial Number is correct and the device is registered with the system.";
        when(deviceService.findBySerialNo(anyString())).thenThrow(new DeviceNotFoundException(message, detail));

        this.mockMvc.perform(get("/api/v1/device/{serialno}",999999999))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(jsonPath("$.status" ).value("NOT_FOUND"))
                .andExpect(jsonPath("$.error_code" ).value("NOT_FOUND"))
                .andExpect(jsonPath("$.message" ).value(message))
                .andExpect(jsonPath("$.detail" ).value(detail));
    }
}