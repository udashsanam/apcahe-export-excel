package com.cvrs.backend.controller.base;

import com.cvrs.backend.dto.CustomDto.ContactDto;
import com.cvrs.backend.dto.CustomDto.ResponseDto;
import com.cvrs.backend.service.implementation.CvrsMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@CrossOrigin(origins = {
        "http://localhost:3000",
        "http://192.168.1.2:3000",
        "http://192.168.1.74:3000",
        "http://192.168.1.68:3000",
        "http://192.168.0.136:3000",
        "http://192.168.1.176:3000",
        "http://192.168.1.248:3000",
        "http://192.168.1.71:3000",

})
public class BaseController {


}
