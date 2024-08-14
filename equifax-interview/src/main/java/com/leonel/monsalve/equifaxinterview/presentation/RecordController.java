package com.leonel.monsalve.equifaxinterview.presentation;

import com.leonel.monsalve.equifaxinterview.data.entity.Record;
import com.leonel.monsalve.equifaxinterview.domain.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/record")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RecordController {
    private final RecordService recordService;

    @SneakyThrows
    @PostMapping
    public ResponseEntity<List<Record>> getFile(@RequestParam("file") MultipartFile file){
        List<Record> records = recordService.processExcel(file);
        return ResponseEntity.ok(records);
    }
}
