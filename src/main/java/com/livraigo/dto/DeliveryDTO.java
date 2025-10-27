package com.livraigo.dto;

import com.livraigo.model.enums.DeliveryStatus;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
public class DeliveryDTO {
    
    private Long id;
    
    @NotNull(message = "L'adresse est obligatoire")
    private String address;
    
    @NotNull(message = "La latitude est obligatoire")
    @DecimalMin(value = "-90.0", message = "La latitude doit être entre -90 et 90")
    @DecimalMin(value = "90.0", message = "La latitude doit être entre -90 et 90")
    private Double latitude;
    
    @NotNull(message = "La longitude est obligatoire")
    @DecimalMin(value = "-180.0", message = "La longitude doit être entre -180 et 180")
    @DecimalMin(value = "180.0", message = "La longitude doit être entre -180 et 180")
    private Double longitude;
    
    @NotNull(message = "Le poids est obligatoire")
    @DecimalMin(value = "0.1", message = "Le poids doit être supérieur à 0")
    private Double weight;
    
    @NotNull(message = "Le volume est obligatoire")
    @DecimalMin(value = "0.01", message = "Le volume doit être supérieur à 0")
    private Double volume;
    
    private LocalTime preferredTimeStart;
    private LocalTime preferredTimeEnd;
    
    private DeliveryStatus status;
    
    private Long tourId;
    private Integer order;
}