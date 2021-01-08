package com.mihey.springrestapi.controller;

import com.mihey.springrestapi.dto.RegionDTO;
import com.mihey.springrestapi.model.Region;
import com.mihey.springrestapi.service.Impl.RegionServiceImpl;
import com.mihey.springrestapi.service.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/regions")
public class RegionRestControllerV1 {


    private final RegionServiceImpl regionService;
    private final RegionMapper regionMapper;

    @Autowired
    public RegionRestControllerV1(RegionServiceImpl regionService, RegionMapper regionMapper) {
        this.regionService = regionService;
        this.regionMapper = regionMapper;
    }

    @GetMapping
    public ResponseEntity<List<RegionDTO>> getAllRegions() {
        List<RegionDTO> regions = regionMapper.toDto(regionService.getRegions());
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> getRegionById(@PathVariable int id) {
        RegionDTO region = regionMapper.toDto(regionService.getRegionById(id));
        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Region> saveRegion(@Valid @RequestBody Region region) {
        return new ResponseEntity<>(regionService.saveRegion(region), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RegionDTO> updateRegion(@RequestBody RegionDTO region) {
        RegionDTO r = regionMapper.toDto(regionService.updateRegion(regionMapper.toEntity(region)));
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegionById(@PathVariable int id) {
        regionService.deleteRegionById(id);
        return new ResponseEntity<>("Region successfully deleted", HttpStatus.OK);
    }

}
