package com.isft194.gestin.controllers;

import com.isft194.gestin.models.Exam;
import com.isft194.gestin.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/apigestin/exam")
public class ExamController extends BaseController<Exam, ExamService>
{
    @Autowired
    private ExamService examService;

}
