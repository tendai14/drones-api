package com.musala.droneapi.services.impl;

import com.musala.droneapi.model.Drone;
import com.musala.droneapi.services.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneService {

    @Autowired
    private DroneRepository droneRepository;

    public Drone saveDrone(Drone drone){
        return  droneRepository.save(drone);
    }

}
