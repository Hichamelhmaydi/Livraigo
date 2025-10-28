package com.livraigo.dto;

import com.livraigo.model.entity.enums.VehicleType;
import lombok.Data;

@Data
public class VehicleRequestDTO {
    private String licensePlate;
    private VehicleType type;
    private Boolean available;
}