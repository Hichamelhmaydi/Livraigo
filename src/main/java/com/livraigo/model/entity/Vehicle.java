package com.livraigo.model.entity;

import com.livraigo.model.enums.VehicleType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "La plaque d'immatriculation est obligatoire")
    @Column(unique = true, nullable = false)
    private String licensePlate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType type;
    
    @DecimalMin(value = "0.1", message = "La capacité de poids doit être supérieure à 0")
    @Column(nullable = false)
    private Double maxWeight;
    
    @DecimalMin(value = "0.1", message = "La capacité de volume doit être supérieure à 0")
    @Column(nullable = false)
    private Double maxVolume;
    
    @Column(nullable = false)
    private Integer maxDeliveries;
    
    private Boolean available;
}