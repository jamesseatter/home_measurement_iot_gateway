package eu.seatter.homeheating.edge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.seatter.homeheating.edge.commands.RegistrationCommand;
import eu.seatter.homeheating.edge.converters.DeviceToRegistrationCommand;
import eu.seatter.homeheating.edge.model.Device;
import eu.seatter.homeheating.edge.model.RegistrationStatus;
import eu.seatter.homeheating.edge.service.DeviceService;
import eu.seatter.homeheating.edge.service.RegistrationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 25/01/2019
 * Time: 11:22
 */
@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DeviceToRegistrationCommand converterDeviceToRegistrationCommand;

    @MockBean
    private RegistrationService registrationService;

    @MockBean
    private DeviceService deviceService;

    private Device device;
    private RegistrationCommand registrationCommand;


    @Before
    public void setUp(){
        device = new Device();
        device.setName("DevNEW");
        device.setManufacturer("Pi");
        device.setUniqueId("12345");
        device.setOperatingSystem("Raspberian");
        device.setRegistrationStatus(RegistrationStatus.PENDINGAPPROVAL);

        registrationCommand = new RegistrationCommand();
        registrationCommand.setName("DevNEW");
        registrationCommand.setManufacturer("Pi");
        registrationCommand.setUniqueId("12345");
        registrationCommand.setOperatingSystem("Raspberian");
        registrationCommand.setRegistrationStatus(RegistrationStatus.PENDINGAPPROVAL);

    }

    @Test
    public void whenPostNewDevice_thenShouldReturnNewDeviceWithHttpStatusCreated() throws Exception {
        //given
        given(registrationService.newDevice(any(RegistrationCommand.class))).willReturn(Optional.of(device));

        //when
        this.mockMvc.perform(post("/api/v1/registration/device/new")
                                .contentType("application/json;charset=UTF-8")
                                .content(asJsonString(registrationCommand)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(registrationCommand.getName()))
                .andExpect(jsonPath("$.registrationStatus").value("PENDINGAPPROVAL"));
        //then
        verify(registrationService,times(1)).newDevice(any(RegistrationCommand.class));
    }

    @Test
    public void whenPostNewDeviceBadJSONInput_thenShouldReturnMethodArgumentNotValidException() throws Exception {
        //given
        RegistrationCommand badrc = new RegistrationCommand(); //any incomplete RegistrationCommand is an exception.

        given(registrationService.newDevice(any(RegistrationCommand.class))).willReturn(Optional.of(device));

        //when
        this.mockMvc.perform(post("/api/v1/registration/device/new")
                .contentType("application/json;charset=UTF-8")
                .content(asJsonString(badrc)))
                .andExpect(status().isBadRequest());
        //then
        verify(registrationService,times(0)).newDevice(any(RegistrationCommand.class));
    }


    @Test
    public void whenGetRegistrationGood_thenShouldReturnRegistrationCommandHttpStatusOK() throws Exception {
        //given
        given(registrationService.getRegistration(anyString())).willReturn(Optional.of(device));

        //when
        this.mockMvc.perform(get("/api/v1/registration/device")
                                            .param("uniqueId", "%2B4UKP%2BJdIfG6vvbYtmk9nL%2BbPFGEiJ11%2F159suwMso0%3D"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(device.getName()))
                .andExpect(jsonPath("$.registrationStatus").value("PENDINGAPPROVAL"));
        //then
        verify(registrationService,times(1)).getRegistration(anyString());
    }

    @Test
    public void whenGetRegistrationBADID_thenShouldReturnUnsupportedMediaTypeException() throws Exception {
        //given
        RegistrationCommand badrc = new RegistrationCommand(); //any incomplete RegistrationCommand is an exception.

        given(registrationService.getRegistration(anyString())).willReturn(Optional.of(device));

        //when
        this.mockMvc.perform(get("/api/v1/registration/device")
                                .param("uniqueId", "BADID")
                                )
                .andExpect(jsonPath("$.id").isEmpty());

        //then
        verify(registrationService,times(0)).newDevice(any(RegistrationCommand.class));
    }



    /*
     * converts a Java object into JSON representation
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}