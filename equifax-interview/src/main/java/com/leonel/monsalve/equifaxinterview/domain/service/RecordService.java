package com.leonel.monsalve.equifaxinterview.domain.service;

import com.leonel.monsalve.equifaxinterview.data.entity.Record;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RecordService {
    List<Record> processExcel(MultipartFile excel) throws IOException;
}
