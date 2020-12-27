package com.mihey.springrestapi.controller;

import com.mihey.springrestapi.model.Writer;
import com.mihey.springrestapi.repository.RegionRepository;
import com.mihey.springrestapi.repository.UserRepository;
import com.mihey.springrestapi.repository.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class WriterController {

    @Autowired
    private WriterRepository writerRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/v1/users")
    public Writer addWriter(@Valid @RequestBody Writer writer, Principal user) {
        writer.setUser(userRepository.findByUserName(user.getName()).get());
        writer.setRegion(regionRepository.save(writer.getRegion()));
        return writerRepository.save(writer);
    }
}
