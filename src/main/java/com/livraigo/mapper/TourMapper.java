package com.livraigo.mapper;

import com.livraigo.dto.TourRequestDTO;
import com.livraigo.model.entity.Delivery;
import com.livraigo.model.entity.Tour;
import com.livraigo.model.entity.Vehicle;
import com.livraigo.model.entity.Warehouse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TourMapper {

    private final ModelMapper modelMapper;

    public TourMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        TypeMap<TourRequestDTO, Tour> typeMap = this.modelMapper.createTypeMap(TourRequestDTO.class, Tour.class);

        typeMap.addMappings(mapper -> {
            mapper.skip(Tour::setId);

            mapper.map(TourRequestDTO::getName, Tour::setName);
            mapper.map(TourRequestDTO::getDate, Tour::setDate);
            mapper.map(TourRequestDTO::getAlgorithm, Tour::setAlgorithm);

            mapper.<Long>map(TourRequestDTO::getVehicleId, (dest, v) -> {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(v);
                dest.setVehicle(vehicle);
            });

            mapper.<Long>map(TourRequestDTO::getWarehouseId, (dest, v) -> {
                Warehouse warehouse = new Warehouse();
                warehouse.setId(v);
                dest.setWarehouse(warehouse);
            });

            mapper.<List<Long>>map(TourRequestDTO::getDeliveryIds, (dest, ids) -> {
                if (ids != null) {
                    List<Delivery> deliveries = new ArrayList<>();
                    for (Long id : ids) {
                        Delivery d = new Delivery();
                        d.setId(id);
                        deliveries.add(d);
                    }
                    dest.setDeliveries(deliveries);
                }
            });
        });
    }

    public Tour toEntity(TourRequestDTO dto) {
        return modelMapper.map(dto, Tour.class);
    }
}
