package com.mihey.springrestapi.service.Impl;

import com.mihey.springrestapi.model.Code;
import com.mihey.springrestapi.model.Code;
import com.mihey.springrestapi.repository.CodeRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(SpringRunner.class)
@SpringBootTest
class CodeServiceImplTest {
    @Autowired
    private CodeServiceImpl codeService;
    @MockBean
    private CodeRepository codeRepository;

    Code code = new Code();

    @Test
    void getCodesTest() {
        List<Code> codes = new ArrayList<>();
        codes.add(code);
        Mockito.when(codeRepository.findAll()).thenReturn(codes);
        assertEquals(codes, codeService.getCodes());
        assertNotEquals(0, codeService.getCodes().size());
    }

    @Test
    void getCodeByIdTest() {
        code.setUserId(1);
        Optional<Code> c = Optional.of(code);
        Mockito.when(codeRepository.findById(anyInt())).thenReturn(c);
        assertFalse(codeService.getCodeById(1).isConfirmed());
        assertEquals(1, codeService.getCodeById(1).getUserId());
    }

    @Test
    void saveCodeTest() {
        Mockito.when(codeRepository.save(code)).thenReturn(code);
        assertEquals(code, codeService.saveCode(code));
        assertNotEquals("Jon", codeService.saveCode(code).getCode());
    }

    @Test
    void updateCodeTest() {
        code.setCode("11111");
        Mockito.when(codeRepository.save(code)).thenReturn(code);
        assertEquals(code, codeService.saveCode(code));
        assertEquals("11111", codeService.saveCode(code).getCode());
        assertNotEquals("Jon", codeService.saveCode(code).getCode());
    }

    @Test
    void deleteCodeByIdTest() {
        Mockito.doNothing().when(codeRepository).deleteById(anyInt());
        codeService.deleteCodeById(1);
    }
}