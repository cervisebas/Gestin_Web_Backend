package com.isft194.gestin.controllers;

import com.isft194.gestin.services.AcademicRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/academic_records")
public class AcademicRecordController {

    @Autowired
    private AcademicRecordService academicRecordService;
}
