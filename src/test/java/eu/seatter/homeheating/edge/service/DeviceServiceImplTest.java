package eu.seatter.homeheating.edge.service;

import eu.seatter.homeheating.edge.model.Device;
import eu.seatter.homeheating.edge.repository.DeviceRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 10/01/2019
 * Time: 09:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceServiceImplTest {

    @Autowired
    private DeviceServiceImpl deviceService;

    @MockBean
    private DeviceRepository deviceRepository;

    private Device returnedDevice;

    @Before
    public void setUp() {
        returnedDevice = Device.builder().name("pi3n1").manufacturer("pi").uniqueId("112233").operatingSystem("raspberian").build();
    }

    @Test
    public void whenFindById_thenDeviceShouldBeFound() {
        //given
        when(deviceRepository.findById(anyLong())).thenReturn(Optional.of(returnedDevice));

        //when
        Device foundDevice = deviceService.findById(1L).orElse(new Device());

        //then
        assertThat(foundDevice.getId()).isEqualTo(returnedDevice.getId());
    }

    @Test
    public void whenFindByBadId_thenEmptyDevice() {
        //given
        when(deviceRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when
        Device foundDevice = deviceService.findById(10000L).orElse(new Device());

        //then
        assertThat(foundDevice.getName()).isNull();
    }

    @Test
    public void whenFindAll_thenDevicesShouldBeFound() {
        //given
        Set<Device> deviceSet = new HashSet<>();
        deviceSet.add(Device.builder().name("pi3n1").manufacturer("pi").uniqueId("112233").operatingSystem("raspberian").build());
        deviceSet.add(Device.builder().name("pi3n2").manufacturer("pi").uniqueId("998877").operatingSystem("raspberian").build());
        when(deviceRepository.findAll()).thenReturn(deviceSet);

        //when
        Set<Device> foundDevices = deviceService.findAll();

        //then
        assertThat(foundDevices.size()).isEqualTo(2);
    }



    @Test
    public void whenFindByName_thenDeviceShouldBeFound() {
        //given
        when(deviceRepository.findByName(anyString())).thenReturn(Optional.of(returnedDevice));

        //when
        Device foundDevice = deviceService.findByName(returnedDevice.getName()).orElse(new Device());

        //then
        assertThat(foundDevice.getName()).isEqualTo(returnedDevice.getName());
    }

    @Test
    public void whenFindByBadName_thenDeviceShouldBeNotFound() {
        //given
        when(deviceRepository.findByName(anyString())).thenReturn(Optional.empty());

        //when
        Device foundDevice = deviceService.findByName("BADNAME").orElse(new Device());

        //then
        assertThat(foundDevice.getName()).isNull();
    }

    @Test
    public void whenFindByUniqueID_thenDeviceShouldBeFound() {
        //given
        when(deviceRepository.findByUniqueId(anyString())).thenReturn(Optional.of(returnedDevice));

        //when
        Device foundDevice = deviceService.findByUniqueId(returnedDevice.getUniqueId()).orElse(new Device());

        //then
        assertThat(foundDevice.getUniqueId()).isEqualTo(returnedDevice.getUniqueId());
    }

    @Test
    public void whenFindByBadUniqueID_thenDeviceShouldNotBeFound() {
        //given
        when(deviceRepository.findByUniqueId(anyString())).thenReturn(Optional.empty());

        //when
        Device foundDevice = deviceService.findByUniqueId("BADUID").orElse(new Device());

        //then
        assertThat(foundDevice.getUniqueId()).isNull();
    }





}