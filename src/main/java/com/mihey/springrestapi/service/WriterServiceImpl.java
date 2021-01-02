package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.Writer;
import com.mihey.springrestapi.model.dto.WriterDTO;
import com.mihey.springrestapi.repository.WriterRepository;
import com.mihey.springrestapi.service.mapper.WriterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterServiceImpl implements WriterService {

    private WriterRepository writerRepository;
    private WriterMapper writerMapper;

    @Autowired
    public WriterServiceImpl(WriterRepository writerRepository,WriterMapper writerMapper) {
        this.writerRepository = writerRepository;
        this.writerMapper = writerMapper;
    }

    @Override
    public List<WriterDTO> getWriters() {
        return writerMapper.toDto(writerRepository.findAll());
    }

    @Override
    public WriterDTO getWriterById(Integer id) {
        return writerMapper.toDto(writerRepository.findById(id).get());
    }

    @Override
    public WriterDTO saveWriter(WriterDTO writer) {
        return writerMapper.toDto(writerRepository.save(writerMapper.toEntity(writer)));
    }

    @Override
    public WriterDTO updateWriter(WriterDTO writer) {
        return writerMapper.toDto(writerRepository.save(writerMapper.toEntity(writer)));
    }

    @Override
    public void deleteWriterById(Integer id) {
        writerRepository.deleteById(id);
    }
}
