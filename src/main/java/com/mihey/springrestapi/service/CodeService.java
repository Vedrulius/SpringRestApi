package com.mihey.springrestapi.service;

import com.mihey.springrestapi.model.Code;

import java.util.List;

public interface CodeService {

    List<Code> getCodes();

    Code getCodeById(Integer id);

    Code saveCode(Code code);

    Code updateCode(Code code);

    void deleteCodeById(Integer id);
}

