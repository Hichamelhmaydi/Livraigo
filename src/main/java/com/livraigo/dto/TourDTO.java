package com.livraigo.dto;

import com.livraigo.model.enums.OptimizationAlgorithm;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class TourDTO {
    
    private Long id;
    
    @NotNull(message = "La date est obligatoire")
    private LocalDate date;
    
    @NotNull(message = "Le véhicule est obligatoire")
    private Long vehicleId;
    
    @NotNull(message = "L'entrepôt est obligatoire")
    private Long warehouseId;
    
    @NotNull(message = "L'algorithme est obligatoire")
    private OptimizationAlgorithm algorithm;
    
    private List<Long> deliveryIds = new ArrayList<>();
}