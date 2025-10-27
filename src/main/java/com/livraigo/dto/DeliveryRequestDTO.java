package com.livraigo.dto;

import lombok.Data;
import java.time.LocalTime;

import com.livraigo.model.entity.enums.DeliveryStatus;

@Data
public class DeliveryRequestDTO {
    private String address;
    private Double latitude;
    private Double longitude;
    private Double weight;
    private Double volume;
    private LocalTime preferredTimeStart;
    private LocalTime preferredTimeEnd;
    private DeliveryStatus status;
    private Long tourId;
    private Integer order;
}