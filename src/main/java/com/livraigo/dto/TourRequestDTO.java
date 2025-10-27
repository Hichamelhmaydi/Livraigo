package com.livraigo.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

import com.livraigo.model.entity.enums.OptimizationAlgorithm;

@Data
public class TourRequestDTO {
    private String name;
    private LocalDate date;
    private Long vehicleId;
    private Long warehouseId;
    private OptimizationAlgorithm algorithm;
    private List<Long> deliveryIds;
}