package com.leonel.monsalve.equifaxinterview.domain.service.impl;

import com.leonel.monsalve.equifaxinterview.data.entity.Record;
import com.leonel.monsalve.equifaxinterview.data.repository.RecordRepository;
import com.leonel.monsalve.equifaxinterview.domain.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;

    Workbook workbook = new XSSFWorkbook();

    @Override
    public List<Record> processExcel(MultipartFile excel) throws IOException {
        InputStream inputStream = excel.getInputStream();
        workbook = WorkbookFactory.create(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        List<Record> records = new ArrayList<>();

        for (Row row : firstSheet) {
            if (row.getRowNum() == 0) {
                continue;
            }

            Record newRecord = Record.builder()
                .name(row.getCell(0).getStringCellValue())
                .rut(row.getCell(1).getStringCellValue())
                .field1(row.getCell(2).getStringCellValue())
                .field2(row.getCell(3).getStringCellValue())
                .field3(row.getCell(4).getStringCellValue())
                .build();

            records.add(newRecord);
        }

        recordRepository.saveAll(records);
        inputStream.close();
        workbook.close();
        return records;
    }
}
