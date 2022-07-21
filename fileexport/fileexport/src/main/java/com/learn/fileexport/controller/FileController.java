package com.learn.fileexport.controller;


import com.learn.fileexport.model.StudentEntity;
import com.learn.fileexport.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class FileController {


        private StudentRepository studentRepository;

        @Autowired
    public FileController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

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
                // adding empty array list in date with index i

                data.put(i, new ArrayList<>());
                // reading  all the cell values
                for (Cell cell :
                        row) {
                    // get cell type will get the

                    switch (cell.getCellType()){
                        case STRING:
                            // adding all the content of the row in index
                            data.get(new Integer(i)).add(cell.getRichStringCellValue().getString());
                            break;
                        case NUMERIC:
                            // if the cell contents numeric value
                            // numeric value can be  of two type date of numberi
                            if(DateUtil.isCellDateFormatted(cell)){
                                // reading date formate
                                data.get(new Integer(i)).add(cell.getDateCellValue() + "");
                            } else {
                                // reading numeric value
                                data.get(new Integer(i)).add(cell.getNumericCellValue() + "");
                            }


                            break;
                        case BOOLEAN:
                            // if the cell contents boolean value
                            data.get(new Integer(i)).add(cell.getBooleanCellValue() + "");
                            break;
                        case FORMULA:
                            // if the cell contents formula

                            data.get(new Integer(i)).add(cell.getCellFormula()+ "");

                            break;
                    }
                    
                }
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {

            for (int i = 0; i < data.size(); i++) {
                List<String>  students = data.get(i);
                studentRepository.save(new StudentEntity(students.get(0), students.get(1)));
            }

        } catch (Exception ex){

            log.error(" Error while adding student ");
        }



        return data;
    }
}
