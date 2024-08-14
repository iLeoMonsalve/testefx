package com.leonel.monsalve.equifaxinterview.presentation;

import com.leonel.monsalve.equifaxinterview.data.entity.Record;
import com.leonel.monsalve.equifaxinterview.domain.service.RecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RecordControllerTest {

    @Mock
    private RecordService recordService;

    @InjectMocks
    private RecordController recordController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.xlsx", "application/vnd.ms-excel", new byte[]{1, 2, 3});
        List<Record> expectedRecords = new ArrayList<>();
        expectedRecords.add(new Record());

        when(recordService.processExcel(any(MultipartFile.class))).thenReturn(expectedRecords);

        ResponseEntity<List<Record>> responseEntity = recordController.getFile(file);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedRecords, responseEntity.getBody());
    }
}