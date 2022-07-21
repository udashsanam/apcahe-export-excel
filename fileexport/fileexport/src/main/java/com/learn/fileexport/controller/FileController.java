package com.learn.fileexport.controller;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController {



        // uploading file as multipart file by the use
    @PostMapping()
    public Map<Integer, List<String>> uploadFile(@RequestParam MultipartFile file){

        Map<Integer, List<String>> data = new HashMap<>();
        try {
            // reading the file from the user and crating workbook  class
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            // this will read the sheet from xlsx file
            Sheet sheet = workbook.getSheetAt(0);

            // creating hash map to store the data from Excel file

            int i = 0 ;
        // first reading row
            for (Row row :
                    sheet) {
                data.put(i, new ArrayList<>());
                // readin all the cell values
                for (Cell cell :
                        row) {
                    // get cell type will get the

                    switch (cell.getCellType()){
                        case STRING:
                            // adding all the content of the row in index
                            data.get(new Integer(i)).add(cell.getRichStringCellValue().getString());
                            break;
                    }
                    
                }
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }
}
