package com.musala.droneapi.controllers;

import com.musala.droneapi.model.Drone;
import com.musala.droneapi.model.Medication;
import com.musala.droneapi.services.impl.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
public class DroneRestController {
    @Autowired
    DroneService droneService;

    @PostMapping("drone/register")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Drone> registerUser(@RequestBody @Validated Drone drone){
        return new ResponseEntity<>(droneService.saveDrone(drone), HttpStatus.OK);
    }

    @PostMapping("medication/load/{droneId}")
    public ResponseEntity<Medication> registerMedicationByDrone(@RequestBody @Validated Medication medication, @PathVariable Long droneId){
        return new ResponseEntity<>(droneService.saveMedicationByDroneId(medication, droneId), HttpStatus.OK);
    }



}
