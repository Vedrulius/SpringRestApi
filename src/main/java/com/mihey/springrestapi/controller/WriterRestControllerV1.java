package com.mihey.springrestapi.controller;

import com.mihey.springrestapi.model.Writer;
import com.mihey.springrestapi.model.dto.WriterDTO;
import com.mihey.springrestapi.service.RegionServiceImpl;
import com.mihey.springrestapi.service.UserServiceImpl;
import com.mihey.springrestapi.service.WriterServiceImpl;
import com.mihey.springrestapi.service.mapper.WriterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/writers")
public class WriterRestControllerV1 {

    private WriterServiceImpl writerService;
    private RegionServiceImpl regionService;
    private UserServiceImpl userService;
    private WriterMapper writerMapper;

    @Autowired
    public WriterRestControllerV1(WriterServiceImpl writerService, RegionServiceImpl regionService,
                                  UserServiceImpl userService, WriterMapper writerMapper) {
        this.writerService = writerService;
        this.regionService = regionService;
        this.userService = userService;
        this.writerMapper = writerMapper;
    }

    @PostMapping
    public ResponseEntity<WriterDTO> addWriter(@Valid @RequestBody WriterDTO writer, Principal user) {
        writerMapper.toEntity(writer).setUser(userService.findByUserName(user.getName()).get());
        writer.setRegion(regionService.saveRegion(writer.getRegion()));
        return new ResponseEntity<>(writerService.saveWriter(writer), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<WriterDTO>> getWriters() {
        List<WriterDTO> writers = writerService.getWriters();
        return new ResponseEntity<>(writers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WriterDTO> getWriterById(@PathVariable int id) {
        WriterDTO writer = writerService.getWriterById(id);
        return new ResponseEntity<>(writer, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<WriterDTO> updateRegion(@RequestBody WriterDTO writer) {
        WriterDTO w = writerService.updateWriter(writer);
        return new ResponseEntity<>(w, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegionById(@PathVariable int id) {
        writerService.deleteWriterById(id);
        return new ResponseEntity<>("Writer successfully deleted", HttpStatus.OK);
    }
}
