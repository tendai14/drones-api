package com.musala.droneapi.services.impl;

import com.musala.droneapi.enums.DroneState;
import com.musala.droneapi.model.Drone;
import com.musala.droneapi.model.Medication;
import com.musala.droneapi.services.DroneRepository;
import com.musala.droneapi.services.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public Drone updateDrone(Drone drone, Long droneId, String state) {
        drone.setId(droneId);
        drone.setState(state);
        return saveDrone(drone);
    }

    public Medication saveMedicationByDroneId(Medication medication, Long id){
        Optional<Drone> drone = droneRepository.findById(id);
        Medication medicationObject = new Medication();
        List<Medication> medications = getMedicationByDroneId(id);
        List<Double> weights = new ArrayList<>();
        for (Medication md: medications
             ) {

            weights.add(md.getWeight());
        }
        double sum = weights.stream().mapToDouble(Double::doubleValue).sum();
        if (drone.isPresent() && drone.get().getState().equals(DroneState.IDLE.toString())){
            medication.setDrone(drone.orElse(null));
            if (sum <= 500 && medication.getWeight()<(500-sum)){
                medicationObject =  medicationRepository.save(medication);
                updateDrone(drone.get(), drone.get().getId(), DroneState.LOADING.toString());
            }
        }
        return medicationObject;

    }

    public List<Medication> getMedicationByDroneId(Long droneId){
        Optional<Drone> drone = droneRepository.findById(droneId);
        List<Medication> medications = new ArrayList<>();
        if (drone.isPresent()){
           medications =  medicationRepository.findAll();
        }
        return medications;
    }

    public List<Drone> getAvailableDrones() {
        List<Drone> availableDrones = new ArrayList<>();
        List<Drone> drones = droneRepository.findAll();
        for (Drone drone : drones) {
            if (drone.getBattery_capacity() > 25 && (drone.getState().equals(DroneState.IDLE.toString()) ||
                    drone.getState().equals(DroneState.LOADING.toString())) ){
                availableDrones.add(drone);
            }
        }
        return availableDrones;
    }

    public Double getDroneBatteryLevel(Long droneId) {
        Optional<Drone> drone = droneRepository.findById(droneId);
        return drone.map(Drone::getBattery_capacity).orElse(null);
    }

}
