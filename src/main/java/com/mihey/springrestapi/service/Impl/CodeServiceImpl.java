package com.mihey.springrestapi.service.Impl;

import com.mihey.springrestapi.model.Code;
import com.mihey.springrestapi.repository.CodeRepository;
import com.mihey.springrestapi.service.CodeService;
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
    public List<Code> getCodes() {
        return codeRepository.findAll();
    }

    @Override
    public Code getCodeById(Integer id) {
        return codeRepository.findById(id).get();
    }

    @Override
    public Code saveCode(Code code) {
        return codeRepository.save(code);
    }

    @Override
    public Code updateCode(Code code) {
        return codeRepository.save(code);
    }

    @Override
    public void deleteCodeById(Integer id) {
        if (codeRepository.existsById(id)) {
            codeRepository.deleteById(id);
        }
    }
}
