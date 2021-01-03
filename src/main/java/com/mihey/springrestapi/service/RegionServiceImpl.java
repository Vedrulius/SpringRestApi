package com.mihey.springrestapi.service;

import com.mihey.springrestapi.dto.RegionDTO;
import com.mihey.springrestapi.repository.RegionRepository;
import com.mihey.springrestapi.service.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

     private RegionRepository regionRepository;

     private RegionMapper regionMapper;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository, RegionMapper regionMapper) {
        this.regionRepository = regionRepository;
        this.regionMapper = regionMapper;
    }

    @Override
    public List<RegionDTO> getRegions() {
        return regionMapper.toDto(regionRepository.findAll());
    }

    @Override
    public RegionDTO getRegionById(Integer id) {
        return regionMapper.toDto(regionRepository.findById(id).get());
    }

    @Override
    public RegionDTO saveRegion(RegionDTO region) {
        return regionMapper.toDto(regionRepository.save(regionMapper.toEntity(region)));
    }

    @Override
    public RegionDTO updateRegion(RegionDTO region) {
        return regionMapper.toDto(regionRepository.save(regionMapper.toEntity(region)));
    }

    @Override
    public void deleteRegionById(Integer id) {
        regionRepository.deleteById(id);
    }
}
