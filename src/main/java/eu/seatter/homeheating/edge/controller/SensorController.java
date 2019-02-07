package eu.seatter.homeheating.edge.controller;

import eu.seatter.homeheating.edge.commands.MeasurementCommand;
import eu.seatter.homeheating.edge.commands.SensorCommand;
import eu.seatter.homeheating.edge.converters.MeasurementCommandToMeasurement;
import eu.seatter.homeheating.edge.converters.MeasurementToMeasurementCommand;
import eu.seatter.homeheating.edge.converters.SensorToSensorCommand;
import eu.seatter.homeheating.edge.exceptions.MeasurementNotSavedException;
import eu.seatter.homeheating.edge.exceptions.SensorNotFoundException;
import eu.seatter.homeheating.edge.model.Measurement;
import eu.seatter.homeheating.edge.model.Sensor;
import eu.seatter.homeheating.edge.service.MeasurementService;
import eu.seatter.homeheating.edge.service.SensorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
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

    private final MeasurementToMeasurementCommand convertMeasurementToMeasurementCommand;
    private final MeasurementCommandToMeasurement convertMeasurementCommandToMeasurement;

    public SensorController(SensorService sensorService, MeasurementService measurementService, SensorToSensorCommand convertSensorToSensorCommand, MeasurementToMeasurementCommand convertMeasurementToMeasurementCommand, MeasurementCommandToMeasurement convertMeasurementCommandToMeasurement) {
        this.sensorService = sensorService;
        this.measurementService = measurementService;
        this.convertSensorToSensorCommand = convertSensorToSensorCommand;
        this.convertMeasurementToMeasurementCommand = convertMeasurementToMeasurementCommand;
        this.convertMeasurementCommandToMeasurement = convertMeasurementCommandToMeasurement;
    }

    /**
     * Returns a list of all Sensors in the system
     *
     * @return  A JSON formatted List SensorCommand objects
     */
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

    /**
     * Returns a single sensor.
     *
     * @param id The id of the sensor.
     * @return A JSON formatted SensorCommand object
     */
    @GetMapping(value = "/sensor/{id}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public SensorCommand getSensorById(@PathVariable Long id) {
        log.debug("Entered getSensorById, id=" + id);
        Sensor foundSensors = sensorService.findById(id).orElseThrow(() ->
                new SensorNotFoundException("Sensor with Id " + id + " not found",
                        "Verify the Id is correct and the Sensor is registered with the system"));
        return convertSensorToSensorCommand.convert(foundSensors);
    }

    /**
     * Returns a list of all measurements for the given sensor.
     *
     * @param id  The id of the sensor.
     * @return A JSON formatted List of MeasurementCommand objects
     */
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
                .map(convertMeasurementToMeasurementCommand::convert)
                .collect(Collectors.toList());
    }

    /**
     * @param id  The id of the sensor.
     * @param newMeasurement A JSON formatted MeasurementCommand object in the request body
     * @return A JSON formatted MeasurementCommand object
     */
    @RequestMapping(value = "/sensor/{id}/measurement", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public MeasurementCommand postSensorMeasurement(@PathVariable Long id,@RequestBody MeasurementCommand newMeasurement) {
        log.debug("Entered getSensorMeasurements, id=" + id);
        Sensor sensor = sensorService.findById(id).orElseThrow(() ->
                new SensorNotFoundException("Sensor with Id " + id + " not found",
                        "Verify the Id is correct and the Sensor is registered with the system"));

        Measurement measurement = convertMeasurementCommandToMeasurement.convert(newMeasurement);
        Objects.requireNonNull(measurement).setSensor(sensor);


        Measurement savedMeasurement = measurementService.save(measurement).orElseThrow(() ->
                new MeasurementNotSavedException("The measurement could not be saved for sensor ID " + id,
                        "Verify all the data is correct in the Posted JSON and try again"));

        sensor.getMeasurements().add(savedMeasurement);

        return convertMeasurementToMeasurementCommand.convert(savedMeasurement);
    }
}
