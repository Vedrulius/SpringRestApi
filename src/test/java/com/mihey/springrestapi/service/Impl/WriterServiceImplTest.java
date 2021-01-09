package com.mihey.springrestapi.service.Impl;

import com.mihey.springrestapi.model.Region;
import com.mihey.springrestapi.model.Writer;
import com.mihey.springrestapi.model.Writer;
import com.mihey.springrestapi.repository.WriterRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
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
class WriterServiceImplTest {

    @Autowired
    private WriterServiceImpl writerService;
    @MockBean
    private WriterRepository writerRepository;

    Writer writer = new Writer();
    Region region = new Region();

    {
        region.setId(1);
        region.setName("US");
        writer.setId(1);
        writer.setFirstName("John");
        writer.setLastName("Doe");
        writer.setRegion(region);
    }

    @Test
    void getWritersTest() {
        List<Writer> writers = new ArrayList<>();
        writers.add(writer);
        Mockito.when(writerRepository.findAll()).thenReturn(writers);
        assertEquals(writers, writerService.getWriters());
        assertNotEquals(0, writerService.getWriters().size());
    }

    @Test
    void getWriterByIdTest() {
        Optional<Writer> r = Optional.of(writer);
        Mockito.when(writerRepository.findById(anyInt())).thenReturn(r);
        assertEquals("John", writerService.getWriterById(1).getFirstName());
        assertNotEquals("Ivan", writerService.getWriterById(1).getFirstName());
    }

    @Test
    void saveWriterTest() {
        Mockito.when(writerRepository.save(writer)).thenReturn(writer);
        assertEquals(writer, writerService.saveWriter(writer));
        assertNotEquals("Jon", writerService.saveWriter(writer).getFirstName());
    }

    @Test
    void updateWriterTest() {
        writer.setFirstName("Ivan");
        Mockito.when(writerRepository.save(writer)).thenReturn(writer);
        assertEquals(writer, writerService.saveWriter(writer));
        assertEquals("Ivan", writerService.saveWriter(writer).getFirstName());
        assertNotEquals("Jon", writerService.saveWriter(writer).getFirstName());
    }

    @Test
    void deleteWriterByIdTest() {
        Mockito.doNothing().when(writerRepository).deleteById(anyInt());
        writerService.deleteWriterById(1);
    }
}