package com.mihey.springrestapi.controller;

import com.mihey.springrestapi.model.Region;
import com.mihey.springrestapi.service.RegionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/regions")
public class RegionRestControllerV1 {


    private RegionServiceImpl regionService;

    @Autowired
    public RegionRestControllerV1(RegionServiceImpl regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions() {
        List<Region> regions = regionService.getRegions();
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable int id) {
        Region region = regionService.getRegionById(id);
        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Region> saveRegion(@Valid @RequestBody Region region) {
        return new ResponseEntity<>(regionService.saveRegion(region), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Region> updateRegion(@RequestBody Region region) {
        Region r = regionService.updateRegion(region);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegionById(@PathVariable int id) {
        regionService.deleteRegionById(id);
        return new ResponseEntity<>("Region successfully deleted", HttpStatus.OK);
    }

}
