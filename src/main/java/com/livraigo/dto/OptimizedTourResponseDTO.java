package com.livraigo.dto;

import com.livraigo.model.enums.OptimizationAlgorithm;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class OptimizedTourResponseDTO {
    
    @NotNull(message = "L'algorithme est obligatoire")
    private OptimizationAlgorithm algorithm;
    
    @NotNull(message = "L'ID du tour est obligatoire")
    private Long tourId;
    
    private List<Long> deliveryIds = new ArrayList<>();
}