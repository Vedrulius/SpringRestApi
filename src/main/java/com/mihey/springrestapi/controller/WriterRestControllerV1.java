package com.mihey.springrestapi.controller;

import com.mihey.springrestapi.dto.WriterDTO;
import com.mihey.springrestapi.model.Writer;
import com.mihey.springrestapi.service.Impl.RegionServiceImpl;
import com.mihey.springrestapi.service.Impl.UserServiceImpl;
import com.mihey.springrestapi.service.Impl.WriterServiceImpl;
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

    private final WriterServiceImpl writerService;
    private final RegionServiceImpl regionService;
    private final UserServiceImpl userService;
    private final WriterMapper writerMapper;

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
        Writer w = writerMapper.toEntity(writer);
        w.setUser(userService.findByUserName(user.getName()).get());
        w.setRegion(regionService.saveRegion(w.getRegion()));
        writerService.saveWriter(w);
        return new ResponseEntity<>(writerMapper.toDto(w), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<WriterDTO>> getWriters() {
        List<WriterDTO> writers = writerMapper.toDto(writerService.getWriters());
        return new ResponseEntity<>(writers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WriterDTO> getWriterById(@PathVariable int id) {
        WriterDTO writer = writerMapper.toDto(writerService.getWriterById(id));
        return new ResponseEntity<>(writer, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<WriterDTO> updateRegion(@RequestBody WriterDTO writer) {
        WriterDTO w = writerMapper.toDto(writerService.updateWriter(writerMapper.toEntity(writer)));
        return new ResponseEntity<>(w, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegionById(@PathVariable int id) {
        writerService.deleteWriterById(id);
        return new ResponseEntity<>("Writer successfully deleted", HttpStatus.OK);
    }
}
