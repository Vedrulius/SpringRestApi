package com.mihey.springrestapi.service.Impl;

import com.mihey.springrestapi.model.Writer;
import com.mihey.springrestapi.repository.WriterRepository;
import com.mihey.springrestapi.service.WriterService;
import com.mihey.springrestapi.service.mapper.WriterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterServiceImpl implements WriterService {

    private final WriterRepository writerRepository;

    @Autowired
    public WriterServiceImpl(WriterRepository writerRepository, WriterMapper writerMapper) {
        this.writerRepository = writerRepository;
    }

    @Override
    public List<Writer> getWriters() {
        return writerRepository.findAll();
    }

    @Override
    public Writer getWriterById(Integer id) {
        return writerRepository.findById(id).get();
    }

    @Override
    public Writer saveWriter(Writer writer) {
        return writerRepository.save(writer);
    }

    @Override
    public Writer updateWriter(Writer writer) {
        return writerRepository.save(writer);
    }

    @Override
    public void deleteWriterById(Integer id) {
        if (writerRepository.existsById(id)) {
            writerRepository.deleteById(id);
        }
    }
}
