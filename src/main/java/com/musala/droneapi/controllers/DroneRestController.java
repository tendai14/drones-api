package com.musala.droneapi.controllers;

import com.musala.droneapi.enums.DroneState;
import com.musala.droneapi.model.Drone;
import com.musala.droneapi.model.Medication;
import com.musala.droneapi.services.impl.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/")
public class DroneRestController {
    @Autowired
    DroneService droneService;

    @PostMapping("drone/register")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Drone> registerUser(@RequestBody @Validated Drone drone){
        drone.setState(DroneState.IDLE.toString());
        UUID uuid = UUID.randomUUID();
        drone.setSerialNumber(uuid.toString());
        return new ResponseEntity<>(droneService.saveDrone(drone), HttpStatus.OK);
    }

    @PostMapping("medication/load/{droneId}")
    public ResponseEntity<Medication> registerMedicationByDrone(@RequestBody @Validated Medication medication, @PathVariable Long droneId){
        return new ResponseEntity<>(droneService.saveMedicationByDroneId(medication, droneId), HttpStatus.OK);
    }

    @GetMapping("medications/{droneId}")
    public ResponseEntity<List<Medication>> getMedicationsByDroneId(@PathVariable Long droneId){
        return new ResponseEntity<>(droneService.getMedicationByDroneId(droneId), HttpStatus.OK);
    }

    @GetMapping("drone/available")
    public ResponseEntity<List<Drone>> getAvailableDrones(){
        return new ResponseEntity<>(droneService.getAvailableDrones(), HttpStatus.OK);
    }

    @GetMapping("drone/{droneId}/battery-level")
    public ResponseEntity<Double> getDroneBatteryLevel(@PathVariable Long droneId){
        return new ResponseEntity<>(droneService.getDroneBatteryLevel(droneId), HttpStatus.OK);
    }
}
