package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.model.Device;
import eu.seatter.homeheating.edge.repository.DeviceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 09:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceServiceImplTest {

//    @TestConfiguration
//    static class DeviceServiceImplTestContextConfiguration {
//        @Bean
//        public DeviceService deviceService() {
//            return new DeviceServiceImpl();
//        }
//    }

    @Autowired
    private DeviceServiceImpl deviceService;

    @MockBean
    private DeviceRepository deviceRepository;

    @Before
    public void setUp() {
        Device device = new Device();
        device.setName("pi3n1");
        device.setManufacturer("pi");
        device.setSerialNo("abcdef11111");
        device.setOperatingSystem("raspberian");

        Mockito.when(deviceRepository.findByName(device.getName())).thenReturn(device);
        Mockito.when(deviceRepository.findBySerialNo(device.getSerialNo())).thenReturn(device);
    }

    @Test
    public void whenValidateName_thenDeviceShouldBeFound() {
        String deviceName = "pi3n1";
        Device found = deviceService.getDeviceByName(deviceName).orElse(new Device());

        assertThat(found.getName()).isEqualTo(deviceName);
    }

    @Test
    public void whenValidateBadName_thenDeviceShouldBeNotFound() {
        String deviceName = "BADNAME";
        Device found = deviceService.getDeviceByName(deviceName).orElse(new Device());

        assertThat(found.getName()).isNull();
    }

    @Test
    public void whenValidateSerialNo_thenDeviceShouldBeFound() {
        String deviceSN = "abcdef11111";
        Device found = deviceService.getDeviceBySerialNo(deviceSN).orElse(new Device());

        assertThat(found.getSerialNo()).isEqualTo(deviceSN);
    }

    @Test
    public void whenValidateBadSerialNo_thenDeviceShouldNotBeFound() {
        String deviceSN = "BADSERIALNO";
        Device found = deviceService.getDeviceBySerialNo(deviceSN).orElse(new Device());

        assertThat(found.getSerialNo()).isNull();
    }



}