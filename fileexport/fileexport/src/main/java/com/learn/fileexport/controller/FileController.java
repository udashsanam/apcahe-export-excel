package com.learn.fileexport.controller;


import com.learn.fileexport.model.StudentEntity;
import com.learn.fileexport.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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


    @GetMapping()

    public void getCsvFile(HttpServletResponse httpServletResponse) throws IOException {
            // creating workbook object
            Workbook workbook = new XSSFWorkbook();
            // creating xscl with name students
            Sheet sheet = workbook.createSheet("students");
            // setting the size of the column
//            sheet.setColumnWidth(0, 6000);
//            sheet.setColumnWidth(1, 4000);

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);

            // Row for header field
        Row headerRow = sheet.createRow(0);

        // creating cell style for the header
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);


        // creating cells for header/ title column
        // cell number 1 contents title 1
        Cell headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Name");
        headerCell.setCellStyle(headerStyle);

        // cell no 2 contains address
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Address");
        headerCell.setCellStyle(headerStyle);

        // cell not 0 centaind tile Id
        headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Id");
        headerCell.setCellStyle(headerStyle);

        // reading data from database

        List<StudentEntity> studentEntities = studentRepository.findAll();

        int i = 1;
        for (StudentEntity studentEntity :
                studentEntities) {

            // generating i th row
            Row row = sheet.createRow(i);

            // adding value in cell number 0 of generated cell
            Cell cell = row.createCell(0);
            cell.setCellValue(studentEntity.getId());

            // generating cell 1 and adding values
            cell = row.createCell(1);
            cell.setCellValue(studentEntity.getName());

            // generating cell not 2 and adding value
            cell = row.createCell(2);
            cell.setCellValue(studentEntity.getAddress());

            i++;



        }

        // creating output stream to write the file
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        workbook.close();

        // creating inputstream for user for file to save
        ByteArrayInputStream byteArrayInputStream =   new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        // creating response for save the the excel
        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename="+ "hello" + ".xlsx");
        IOUtils.copy(byteArrayInputStream, httpServletResponse.getOutputStream());

    }


}

