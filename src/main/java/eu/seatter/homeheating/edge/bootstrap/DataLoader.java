package eu.seatter.homeheating.edge.bootstrap;

import eu.seatter.homeheating.edge.model.*;
import eu.seatter.homeheating.edge.service.DeviceService;
import eu.seatter.homeheating.edge.service.MeasurementService;
import eu.seatter.homeheating.edge.service.SensorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 16/01/2019
 * Time: 23:18
 */
@Component
public class DataLoader implements CommandLineRunner {
    private final DeviceService deviceService;
    private final SensorService sensorService;
    private final MeasurementService measurementService;

    public DataLoader(DeviceService deviceService, SensorService sensorService, MeasurementService measurementService) {
        this.deviceService = deviceService;
        this.sensorService = sensorService;
        this.measurementService = measurementService;
    }

    @Override
    public void run(String... args) {
        // Get count of petTypes from the entity.
        int count = deviceService.findAll().size();

        // If no petTypes are found then no DB is connected, initialise the data set.
        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {
        Device device = Device.builder()
                .name("pi3n1")
                .manufacturer("pi")
                .serialNo("112233")
                .operatingSystem("raspberian").build();
        Sensor sensor = Sensor.builder()
                .active(true)
                .dateAdded(LocalDateTime.now(ZoneOffset.UTC))
                .dateModified(LocalDateTime.now(ZoneOffset.UTC))
                .sensorId("123456")
                .sensorType(SensorType.ONEWIRE)
                .valueType(SensorValueType.TEMPERATURE)
                .valueUnit(SensorValueUnit.CELSIUS)
                .device(device).build();
        Measurement measurement = Measurement.builder()
                .sensor(sensor)
                .measurementTime(LocalDateTime.now(ZoneOffset.UTC))
                .value(20.0D).build();

        deviceService.save(device);
        sensorService.save(sensor);
        measurementService.save(measurement);
    }
}
