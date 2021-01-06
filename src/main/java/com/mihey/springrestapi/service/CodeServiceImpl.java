package com.mihey.springrestapi.service;

import com.mihey.springrestapi.dto.CodeDTO;
import com.mihey.springrestapi.repository.CodeRepository;
import com.mihey.springrestapi.service.mapper.CodeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {

    private final CodeRepository codeRepository;

    private final CodeMapper codeMapper;

    public CodeServiceImpl(CodeRepository codeRepository, CodeMapper codeMapper) {
        this.codeRepository = codeRepository;
        this.codeMapper = codeMapper;
    }

    @Override
    public List<CodeDTO> getCodes() {
        return codeMapper.toDto(codeRepository.findAll());
    }

    @Override
    public CodeDTO getCodeById(Integer id) {
        return codeMapper.toDto(codeRepository.findById(id).get());
    }

    @Override
    public CodeDTO saveCode(CodeDTO code) {
        return codeMapper.toDto(codeRepository.save(codeMapper.toEntity(code)));
    }

    @Override
    public CodeDTO updateCode(CodeDTO region) {
        return codeMapper.toDto(codeRepository.save(codeMapper.toEntity(region)));
    }

    @Override
    public void deleteCodeById(Integer id) {
        if (codeRepository.existsById(id)) {
            codeRepository.deleteById(id);
        }
    }
}
