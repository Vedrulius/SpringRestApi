package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.Region;
import com.mihey.springrestapi.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public List<Region> getRegions() {
        return regionRepository.findAll();
    }

    @Override
    public Region getRegionById(Integer id) {
        return regionRepository.findById(id).get();
    }

    @Override
    public Region saveRegion(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public Region updateRegion(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public void deleteRegionById(Integer id) {
        regionRepository.deleteById(id);
    }
}
