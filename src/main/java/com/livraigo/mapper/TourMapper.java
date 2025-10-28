package com.livraigo.mapper;

import com.livraigo.dto.TourRequestDTO;
import com.livraigo.model.entity.Tour;
import org.modelmapper.ModelMapper;

public class TourMapper {
    
    private final ModelMapper modelMapper;

    public TourMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    
    public Tour toEntity(TourRequestDTO dto) {
        return modelMapper.map(dto, Tour.class);
    }
    
    public TourRequestDTO toDto(Tour entity) {
        return modelMapper.map(entity, TourRequestDTO.class);
    }
}