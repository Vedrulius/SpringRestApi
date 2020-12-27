package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.Writer;
import com.mihey.springrestapi.repository.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterServiceImpl implements WriterService {
    @Autowired
    private WriterRepository writerRepository;

    @Override
    public List<Writer> getWriters() {
        return writerRepository.findAll();
    }

    @Override
    public Writer getWriterById(Integer id) {
        return writerRepository.findById(id).get();
    }

    @Override
    public Writer saveWriter(Writer region) {
        return writerRepository.save(region);
    }

    @Override
    public Writer updateWriter(Writer region) {
        return writerRepository.save(region);
    }

    @Override
    public void deleteWriterById(Integer id) {
        writerRepository.deleteById(id);
    }
}
