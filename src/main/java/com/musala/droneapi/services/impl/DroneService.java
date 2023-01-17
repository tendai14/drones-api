package com.musala.droneapi.services.impl;

import com.musala.droneapi.model.Drone;
import com.musala.droneapi.model.Medication;
import com.musala.droneapi.services.DroneRepository;
import com.musala.droneapi.services.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DroneService {

    @Autowired
    private DroneRepository droneRepository;


    @Autowired
    private MedicationRepository medicationRepository;

    public Drone saveDrone(Drone drone){
        return  droneRepository.save(drone);
    }

    public Medication saveMedicationByDroneId(Medication medication, Long id){
        Optional<Drone> drone = droneRepository.findById(id);
        Medication medicationObject = new Medication();
        if (drone.isPresent()){
            medication.setDrone(drone.orElse(null));
           medicationObject =  medicationRepository.save(medication);
        }
        return medicationObject;

    }

}
