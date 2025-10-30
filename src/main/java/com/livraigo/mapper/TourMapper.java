package com.livraigo.mapper;

import com.livraigo.dto.TourRequestDTO;
import com.livraigo.model.entity.Tour;
import com.livraigo.model.entity.Vehicle;
import com.livraigo.model.entity.Warehouse;
import org.modelmapper.ModelMapper;

public class TourMapper {

    private final ModelMapper modelMapper;

    public TourMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        // إعداد mapping مخصص لتخطي الحقول الحرجة
        this.modelMapper.typeMap(TourRequestDTO.class, Tour.class)
                .addMappings(mapper -> {
                    mapper.skip(Tour::setId);       // تجاهل الـ ID
                    mapper.skip(Tour::setVehicle);  // تجاهل العلاقة مع المركبة
                    mapper.skip(Tour::setWarehouse);// تجاهل العلاقة مع المستودع
                });
    }

    /**
     * تحويل DTO إلى Entity
     * @param dto TourRequestDTO
     * @param vehicle المركبة المرتبطة بالتورن
     * @param warehouse المستودع المرتبط بالتورن
     * @return Tour entity
     */
    public Tour toEntity(TourRequestDTO dto, Vehicle vehicle, Warehouse warehouse) {
        Tour tour = modelMapper.map(dto, Tour.class);
        tour.setVehicle(vehicle);
        tour.setWarehouse(warehouse);
        return tour;
    }

    /**
     * تحويل Entity إلى DTO
     * @param entity Tour
     * @return TourRequestDTO
     */
    public TourRequestDTO toDto(Tour entity) {
        TourRequestDTO dto = modelMapper.map(entity, TourRequestDTO.class);
        // تعيين IDs للعلاقات إذا لزم
        if(entity.getVehicle() != null) {
            dto.setVehicleId(entity.getVehicle().getId());
        }
        if(entity.getWarehouse() != null) {
            dto.setWarehouseId(entity.getWarehouse().getId());
        }
        return dto;
    }
}
