package com.musala.droneapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Drone")
@Data
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "serial_number",unique = true)
    private String serialNumber;

    private String model;

    private Double weight_limit;

    private Double battery_capacity;

    private String state;

    @JsonIgnore
    @OneToMany(mappedBy = "drone")
    List<Medication> medications;
}
