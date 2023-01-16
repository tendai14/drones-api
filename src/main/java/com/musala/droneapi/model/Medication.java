package com.musala.droneapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Medication")
@Data
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Double weight;

    private String code;

    private String image;

    @JsonIgnore
    @ManyToOne
    private Drone drone;
}
