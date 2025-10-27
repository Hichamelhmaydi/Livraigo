package com.livraigo.dto;

import com.livraigo.model.enums.VehicleType;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
public class VehicleDTO {
    
    private Long id;
    
    @NotNull(message = "La plaque d'immatriculation est obligatoire")
    private String licensePlate;
    
    @NotNull(message = "Le type de véhicule est obligatoire")
    private VehicleType type;
    
    @NotNull(message = "Le poids maximum est obligatoire")
    @DecimalMin(value = "0.1", message = "La capacité de poids doit être supérieure à 0")
    private Double maxWeight;
    
    @NotNull(message = "Le volume maximum est obligatoire")
    @DecimalMin(value = "0.1", message = "La capacité de volume doit être supérieure à 0")
    private Double maxVolume;
    
    @NotNull(message = "Le nombre maximum de livraisons est obligatoire")
    private Integer maxDeliveries;
    
    private Boolean available;
}