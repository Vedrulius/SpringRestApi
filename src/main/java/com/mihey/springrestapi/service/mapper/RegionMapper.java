package com.mihey.springrestapi.service.mapper;

import com.mihey.springrestapi.model.Region;
import com.mihey.springrestapi.model.dto.RegionDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RegionMapper extends EntityMapper<Region, RegionDTO> {
    Region toEntity(RegionDTO regionDTO);

    RegionDTO toDto(Region region);

    List<Region> toEntity(List<RegionDTO> regionDTOS);

    List<RegionDTO> toDto(List<Region> regions);
}
