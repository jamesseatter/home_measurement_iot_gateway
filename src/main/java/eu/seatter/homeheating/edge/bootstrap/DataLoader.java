//package eu.seatter.homeheating.edge.bootstrap;
//
//import eu.seatter.homeheating.edge.model.*;
//import eu.seatter.homeheating.edge.service.DeviceService;
//import eu.seatter.homeheating.edge.service.MeasurementService;
//import eu.seatter.homeheating.edge.service.SensorService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by IntelliJ IDEA.
// * User: jas
// * Date: 16/01/2019
// * Time: 23:18
// */
//@Slf4j
//@Profile("!test")
//@Component
//public class DataLoader implements CommandLineRunner {
//    private final DeviceService deviceService;
//    private final SensorService sensorService;
//    private final MeasurementService measurementService;
//
//    public DataLoader(DeviceService deviceService, SensorService sensorService, MeasurementService measurementService) {
//        this.deviceService = deviceService;
//        this.sensorService = sensorService;
//        this.measurementService = measurementService;
//    }
//
//    @Override
//    public void run(String... args) {
//        // Get count of petTypes from the entity.
//        int count = deviceService.findAll().size();
//
//        // If no petTypes are found then no DB is connected, initialise the data set.
//        if(count == 0) {
//            loadData();
//        }
//    }
//
//    private void loadData() {
//        log.info("Loading Devices");
//        Device dev1 = addDevice("pi3n1","11223344");
//        Device dev2 = addDevice("pi3n2","11244344");
//        Device dev3 = addDevice("pi3n3","11153434");
//        Device dev4 = addDevice("pi3n4","13543233");
//        Device dev5 = addDevice("pi3n5","13544233");
//
//        log.info("Loading Sensors");
//        Sensor sensor1 = addSensor("24153", dev1);
//        Sensor sensor2 = addSensor("566721",dev1);
//        Sensor sensor3 = addSensor("566721",dev2);
//        Sensor sensor4 = addSensor("61223g24",dev3);
//        Sensor sensor5 = addSensor("Ag345522",dev4);
//        Sensor sensor6 = addSensor("35",dev4);
//
//        log.info("Loading Measurements");
//        addMeasurement(sensor1,14);
//        addMeasurement(sensor2,3);
//        addMeasurement(sensor3,24);
//        addMeasurement(sensor4,11);
//        addMeasurement(sensor5,16);
//        addMeasurement(sensor6,16);
//
//        log.info("Loading Complete");
//    }
//
//    private Device addDevice(String name, String uniqueId) {
//        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException ex) {System.out.println(ex.getMessage());}
//        Device device = Device.builder()
//                .name(name)
//                .manufacturer("pi")
//                .uniqueId(uniqueId)
//                .operatingSystem("Raspberian").build();
//        return deviceService.save(device).orElse(new Device());
//    }
//
//    private Sensor addSensor(String sensorId,Device device) {
//        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException ex) {System.out.println(ex.getMessage());}
//        Sensor sensor = Sensor.builder()
//                .sensorId("123456")
//                .active(true)
//                .sensorType(SensorType.ONEWIRE)
//                .valueType(SensorValueType.TEMPERATURE)
//                .valueUnit(SensorValueUnit.CELSIUS)
//                .dateAdded(LocalDateTime.now(ZoneOffset.UTC))
//                .dateModified(LocalDateTime.now(ZoneOffset.UTC))
//                .device(device).build();
//        sensorService.save(sensor);
//        return sensorService.save(sensor).orElse(new Sensor());
//    }
//
//    private void addMeasurement(Sensor sensor, int iterations) {
//        for(int i=0;i < iterations;i++) {
//            try { TimeUnit.MILLISECONDS.sleep(10); } catch (InterruptedException ex) {System.out.println(ex.getMessage());}
//            Measurement measurement = Measurement.builder()
//                    .measurementTime(LocalDateTime.now(ZoneOffset.UTC))
//                    .value(20.0D)
//                    .sensor(sensor).build();
//
//            measurementService.save(measurement);
//        }
//    }
//}
