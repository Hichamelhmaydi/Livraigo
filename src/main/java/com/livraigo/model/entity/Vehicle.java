package com.livraigo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.livraigo.model.entity.enums.VehicleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String licensePlate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType type;
    
    @Column(nullable = false)
    private Double maxWeight;
    
    @Column(nullable = false)
    private Double maxVolume;
    
    @Column(nullable = false)
    private Integer maxDeliveries;
    
    private Boolean available;
    
    @PrePersist
    @PreUpdate
    public void setVehicleConstraints() {
        switch (this.type) {
            case BIKE:
                this.maxWeight = 50.0;
                this.maxVolume = 0.5;
                this.maxDeliveries = 15;
                break;
            case VAN:
                this.maxWeight = 1000.0;
                this.maxVolume = 8.0;
                this.maxDeliveries = 50;
                break;
            case TRUCK:
                this.maxWeight = 5000.0;
                this.maxVolume = 40.0;
                this.maxDeliveries = 100;
                break;
        }
        if (this.available == null) {
            this.available = true;
        }
    }
}