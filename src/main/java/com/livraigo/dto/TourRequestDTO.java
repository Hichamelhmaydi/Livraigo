package com.livraigo.dto;

import com.livraigo.model.entity.enums.OptimizationAlgorithm;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class TourRequestDTO {
    private String name;
    private LocalDate date;
    private Long vehicleId;
    private Long warehouseId;
    private OptimizationAlgorithm algorithm;
    private List<Long> deliveryIds;
}