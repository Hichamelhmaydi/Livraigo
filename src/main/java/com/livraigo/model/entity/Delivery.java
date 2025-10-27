package com.livraigo.model.entity;

import com.livraigo.model.enums.DeliveryStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "deliveries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "L'adresse est obligatoire")
    @Column(nullable = false)
    private String address;
    
    @DecimalMin(value = "-90.0", message = "La latitude doit être entre -90 et 90")
    @DecimalMin(value = "90.0", message = "La latitude doit être entre -90 et 90")
    @Column(nullable = false)
    private Double latitude;
    
    @DecimalMin(value = "-180.0", message = "La longitude doit être entre -180 et 180")
    @DecimalMin(value = "180.0", message = "La longitude doit être entre -180 et 180")
    @Column(nullable = false)
    private Double longitude;
    
    @DecimalMin(value = "0.1", message = "Le poids doit être supérieur à 0")
    @Column(nullable = false)
    private Double weight;
    
    @DecimalMin(value = "0.01", message = "Le volume doit être supérieur à 0")
    @Column(nullable = false)
    private Double volume;
    
    private LocalTime preferredTimeStart;
    private LocalTime preferredTimeEnd;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;
    
    @Column(name = "delivery_order")
    private Integer order;
}