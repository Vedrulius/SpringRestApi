package com.mihey.springrestapi.controller;

import com.mihey.springrestapi.model.User;
import com.mihey.springrestapi.model.Writer;
import com.mihey.springrestapi.repository.RegionRepository;
import com.mihey.springrestapi.repository.UserRepository;
import com.mihey.springrestapi.repository.WriterRepository;
import com.mihey.springrestapi.service.RegionServiceImpl;
import com.mihey.springrestapi.service.UserServiceImpl;
import com.mihey.springrestapi.service.WriterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class WriterRestControllerV1 {

    @Autowired
    private WriterServiceImpl writerService;

    @Autowired
    private RegionServiceImpl regionService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/api/v1/writers")
    public Writer addWriter(@Valid @RequestBody Writer writer, Principal user) {
        writer.setUser(userService.findByUserName(user.getName()).get());
        writer.setRegion(regionService.saveRegion(writer.getRegion()));
        return writerService.saveWriter(writer);
    }

    @GetMapping("/api/v1/writers")
    public List<Writer> getWriters() {
        return writerService.getWriters();
    }

    @GetMapping("/api/v1/writers/{id}")
    public ResponseEntity<Writer> getWriterById(@PathVariable int id) {
        Writer writer = writerService.getWriterById(id);
        return new ResponseEntity<>(writer, HttpStatus.OK);
    }

    @PutMapping("/api/v1/writers")
    public ResponseEntity<Writer> updateRegion(@RequestBody Writer writer) {
        Writer w = writerService.saveWriter(writer);
        return new ResponseEntity<>(w, HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/writers/{id}")
    public ResponseEntity<String> deleteRegionById(@PathVariable int id) {
        writerService.deleteWriterById(id);
        return new ResponseEntity<>("Writer successfully deleted", HttpStatus.OK);
    }
}
