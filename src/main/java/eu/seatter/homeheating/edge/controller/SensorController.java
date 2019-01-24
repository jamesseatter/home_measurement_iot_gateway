package eu.seatter.homeheating.edge.controller;

import eu.seatter.homeheating.edge.commands.MeasurementCommand;
import eu.seatter.homeheating.edge.commands.SensorCommand;
import eu.seatter.homeheating.edge.converters.MeasurementToMeasurementCommand;
import eu.seatter.homeheating.edge.converters.SensorToSensorCommand;
import eu.seatter.homeheating.edge.exceptions.SensorNotFoundException;
import eu.seatter.homeheating.edge.model.Measurement;
import eu.seatter.homeheating.edge.model.Sensor;
import eu.seatter.homeheating.edge.service.MeasurementService;
import eu.seatter.homeheating.edge.service.SensorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 20/01/2019
 * Time: 01:24
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class SensorController {

    private final SensorService sensorService;

    private final MeasurementService measurementService;

    private final SensorToSensorCommand convertSensorToSensorCommand;

    private final MeasurementToMeasurementCommand converterMeasurementToMeasurementCommand;

    public SensorController(SensorService sensorService, MeasurementService measurementService, SensorToSensorCommand convertSensorToSensorCommand, MeasurementToMeasurementCommand converterMeasurementToMeasurementCommand) {
        this.sensorService = sensorService;
        this.measurementService = measurementService;
        this.convertSensorToSensorCommand = convertSensorToSensorCommand;
        this.converterMeasurementToMeasurementCommand = converterMeasurementToMeasurementCommand;
    }

    @GetMapping(value = {"sensors","sensors/"}, produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public List<SensorCommand> getAllSensors() {
        log.debug("Entered getAllSensors");
        Set<Sensor> foundSensors = sensorService.findAll();
        return foundSensors.stream()
                .sorted()
                .map(convertSensorToSensorCommand::convert)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/sensor/{id}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public SensorCommand getSensorById(@PathVariable Long id) {
        log.debug("Entered getSensorById, id=" + id);
        Sensor foundSensors = sensorService.findById(id).orElseThrow(() ->
                new SensorNotFoundException("Sensor with Id " + id + " not found",
                        "Verify the Id is correct and the Sensor is registered with the system"));
        return convertSensorToSensorCommand.convert(foundSensors);
    }

    @GetMapping(value = "/sensor/{id}/measurements", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public List<MeasurementCommand> getSensorMeasurements(@PathVariable Long id) {
        log.debug("Entered getSensorMeasurements, id=" + id);
        Sensor foundSensors = sensorService.findById(id).orElseThrow(() ->
                new SensorNotFoundException("Sensor with Id " + id + " not found",
                        "Verify the Id is correct and the Sensor is registered with the system"));

        Set<Measurement> measurements = measurementService.findAllBySensor(foundSensors);
        return measurements.stream()
                .sorted()
                .map(converterMeasurementToMeasurementCommand::convert)
                .collect(Collectors.toList());
    }
}
