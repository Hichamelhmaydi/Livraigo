package com.livraigo.model.entity;

import com.livraigo.model.entity.enums.DeliveryStatus;
import lombok.*;

import javax.persistence.*;
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
    
    @Column(nullable = false)
    private String address;
    
    @Column(nullable = false)
    private Double latitude;
    
    @Column(nullable = false)
    private Double longitude;
    
    @Column(nullable = false)
    private Double weight; 
    
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