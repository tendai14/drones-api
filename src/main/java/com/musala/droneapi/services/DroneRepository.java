package com.musala.droneapi.services;

import com.musala.droneapi.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {

}
